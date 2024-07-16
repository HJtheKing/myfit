package com.chj.myfit.api;

import com.chj.myfit.model.dto.Inbody;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.StringTokenizer;

@Component
public class BoyerMoore {

	private final int UNICODE_SIZE = 65536;

	public void badCharHeuristic(char[] pattern, int size, int[] badChar) {
		Arrays.fill(badChar, -1);
		for (int i = 0; i < size; i++) {
			badChar[(int) pattern[i]] = i;
		}
	}

	public String search(String txt, String pat) {
		StringBuilder sb = new StringBuilder();
		
		char[] text = txt.toCharArray();
		char[] pattern = pat.toCharArray();
		int m = pattern.length;
		int n = text.length;

		int[] badChar = new int[UNICODE_SIZE];
		badCharHeuristic(pattern, m, badChar);

		int s = 0;
		while (s <= (n - m)) {
			int j = m - 1;

			while (j >= 0 && pattern[j] == text[s + j])
				j--;

			if (j < 0) {
				int idx = s + 13;
				int len = s + 14;
				while (txt.charAt(len) != '\"') len++;
				sb.append(txt.substring(idx, len)).append(' ');

				s += (s + m < n) ? m - badChar[text[s + m]] : 1;
			} else {
				s += Math.max(1, j - badChar[text[s + j]]);
			}
		}
		return sb.toString();
	}

	public Inbody extractKeyInfo(String text) {
        String pattern = "\"inferText\":\"";
        String data = search(text, pattern);
        StringTokenizer st = new StringTokenizer(data);

        Inbody inbody = new Inbody();

        String curr = null;

        // 신장, 연령, 성별
        while (!((curr = st.nextToken()).length() >= 2 && curr.charAt(curr.length() - 2) == 'c' && curr.charAt(curr.length() - 1) == 'm')) {}
        inbody.setHeight(Float.parseFloat(curr.substring(0, curr.length() - 2)));
        inbody.setAge(Integer.parseInt(st.nextToken()));
        inbody.setGender(st.nextToken());
        curr = st.nextToken().replace('.', '-');
        curr = curr.charAt(curr.length() - 1) == '-' ? curr.substring(0, curr.length() - 1) : curr.substring(0, curr.length());
        inbody.setMeasurementDate(LocalDate.parse(curr));

        // 체지방량
        while (!(curr = st.nextToken()).equals("체지방") && !curr.equals("체지방량")) {
        }
        st.nextToken();
        inbody.setBodyFatMass(Float.parseFloat(st.nextToken()));

        // 적정체중, 체중조절, 지방조절, 근육조절 - 핵심 데이터 1 / 적정체중 + 체중조절 로 체중 구함 / 지방, 근육 조절에 따른 유산소 및 근력 운동 추천
        // 적정체중
        while (!(curr = st.nextToken()).equals("적정체중")) {}
        curr = st.nextToken();
        inbody.setIdealWeight(curr.charAt(curr.length() - 1) == 'g' ? Float.parseFloat(curr.substring(0, curr.length() - 2)) : Float.parseFloat(curr));

        // 체중조절
        while (!(curr = st.nextToken()).equals("체중조절")) {}
        curr = st.nextToken();
        int sign = -1;
        if (curr.length() == 1) curr += st.nextToken(); // 부호만 들어가있는 경우
        if (curr.charAt(0) == '+') sign = 1;
        inbody.setWeightControl(curr.charAt(curr.length() - 2) == '.' ? sign * Float.parseFloat(curr.substring(1, curr.length())) : sign * Float.parseFloat((curr.substring(1, curr.length() - 2))));

        // 체중
        inbody.setWeight(inbody.getIdealWeight() - inbody.getWeightControl());

        // 지방조절
        while (!(curr = st.nextToken()).equals("지방조절")) {}
        curr = st.nextToken();
        sign = -1;
        if (curr.length() == 1) curr += st.nextToken(); // 부호만 들어가있는 경우
        if (curr.charAt(0) == '+') sign = 1;
        inbody.setFatControl(curr.charAt(curr.length() - 2) == '.' ? sign * Float.parseFloat(curr.substring(1, curr.length())) : sign * Float.parseFloat((curr.substring(1, curr.length() - 2))));

        // 근육조절
        while (!(curr = st.nextToken()).equals("근육조절")) {}
        curr = st.nextToken();
        sign = -1;
        if (curr.length() == 1) curr += st.nextToken(); // 부호만 들어가있는 경우
        if (curr.charAt(0) == '+') sign = 1;
        inbody.setMuscleControl(curr.charAt(curr.length() - 2) == '.' ? sign * Float.parseFloat(curr.substring(1, curr.length())) : sign * Float.parseFloat((curr.substring(1, curr.length() - 2))));

        // 골격근량
        while (!(curr = st.nextToken()).equals("골격근량")) {}
        while (!((curr = st.nextToken()).length() >= 2 && curr.charAt(curr.length() - 2) == '.')) {}
        inbody.setSkeletalMuscleMass(Float.parseFloat(curr));

        // 기초대사량
        while (!(curr = st.nextToken()).equals("기초대사량")) {}
        while (true) {
            curr = st.nextToken();
            try {
                Double.parseDouble(curr);
                break;
            } catch (NumberFormatException e) {
            }
            if (curr.length() >= 4) {
                if (curr.charAt(curr.length() - 4) == 'k' || curr.charAt(curr.length() - 3) == 'c' || curr.charAt(curr.length() - 2) == 'a' || curr.charAt(curr.length() - 1) == 'l') {
                    curr = curr.substring(0, curr.length() - 4);
                    break;
                }
            }
        }
        inbody.setBasalMetabolicRate(Float.parseFloat(curr));

        // 권장섭취열량
        while (!(curr = st.nextToken()).equals("권장섭취열량")) {}
        while (true) {
            curr = st.nextToken();
            try {
                Double.parseDouble(curr);
                break;
            } catch (NumberFormatException e) {
            }
            if (curr.length() >= 4) {
                if (curr.charAt(curr.length() - 4) == 'k' || curr.charAt(curr.length() - 3) == 'c' || curr.charAt(curr.length() - 2) == 'a' || curr.charAt(curr.length() - 1) == 'l') {
                    curr = curr.substring(0, curr.length() - 4);
                    break;
                }
            }
        }
        inbody.setRecommendedIntakeCalories(Float.parseFloat(curr));

        // 부위별근육분석, 부위별체지방분석
        while (!((curr = st.nextToken()).length() >= 2 && curr.substring(0, 2).equals("표준"))) {}
        inbody.setLeftArmMuscle(curr);
        inbody.setRightArmMuscle(st.nextToken());
        inbody.setLeftArmFat(st.nextToken());
        inbody.setRightArmFat(st.nextToken());
        while (!((curr = st.nextToken()).length() >= 2 && curr.substring(0, 2).equals("표준"))) {}
        inbody.setTrunkMuscle(curr);
        while (!((curr = st.nextToken()).length() >= 2 && curr.substring(0, 2).equals("표준"))) {}
        inbody.setTrunkFat(curr);
        while (!((curr = st.nextToken()).length() >= 2 && curr.substring(0, 2).equals("표준"))) {}
        inbody.setLeftLegMuscle(curr);
        inbody.setRightLegMuscle(st.nextToken());
        while (!((curr = st.nextToken()).length() >= 2 && curr.substring(0, 2).equals("표준"))) {}
        inbody.setLeftLegFat(curr);
        inbody.setRightLegFat(st.nextToken());

        return inbody;

	}
}
