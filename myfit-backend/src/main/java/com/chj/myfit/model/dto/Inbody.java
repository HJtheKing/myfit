package com.chj.myfit.model.dto;

import java.time.LocalDate;

public class Inbody {


	private int inbodyId;
	private int memberId;
	private LocalDate measurementDate;
	private float height;
	private int age;
	private String gender;
	private float bodyFatMass;
	private float weight;
	private float skeletalMuscleMass;
	private float idealWeight;
	private float weightControl;
	private float fatControl;
	private float muscleControl;
	private float basalMetabolicRate;
	private float recommendedIntakeCalories;
	private String leftArmMuscle;
	private String rightArmMuscle;
	private String trunkMuscle;
	private String leftLegMuscle;
	private String rightLegMuscle;
	private String leftArmFat;
	private String rightArmFat;
	private String trunkFat;
	private String leftLegFat;
	private String rightLegFat;
	
	
	@Override
	public String toString() {
		return "Inbody [inbodyId=" + inbodyId + ", memberId=" + memberId + ", measurementDate=" + measurementDate
				+ ", height=" + height + ", age=" + age + ", gender=" + gender + ", bodyFatMass=" + bodyFatMass
				+ ", weight=" + weight + ", skeletalMuscleMass=" + skeletalMuscleMass + ", idealWeight=" + idealWeight
				+ ", weightControl=" + weightControl + ", fatControl=" + fatControl + ", muscleControl=" + muscleControl
				+ ", basalMetabolicRate=" + basalMetabolicRate + ", recommendedIntakeCalories="
				+ recommendedIntakeCalories + ", leftArmMuscle=" + leftArmMuscle + ", rightArmMuscle=" + rightArmMuscle
				+ ", trunkMuscle=" + trunkMuscle + ", leftLegMuscle=" + leftLegMuscle + ", rightLegMuscle="
				+ rightLegMuscle + ", leftArmFat=" + leftArmFat + ", rightArmFat=" + rightArmFat + ", trunkFat="
				+ trunkFat + ", leftLegFat=" + leftLegFat + ", rightLegFat=" + rightLegFat + "]";
	}
	public int getInbodyId() {
		return inbodyId;
	}
	public void setInbodyId(int inbodyId) {
		this.inbodyId = inbodyId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public LocalDate getMeasurementDate() {
		return measurementDate;
	}
	public void setMeasurementDate(LocalDate measurementDate) {
		this.measurementDate = measurementDate;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getBodyFatMass() {
		return bodyFatMass;
	}
	public void setBodyFatMass(float bodyFatMass) {
		this.bodyFatMass = bodyFatMass;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getSkeletalMuscleMass() {
		return skeletalMuscleMass;
	}
	public void setSkeletalMuscleMass(float skeletalMuscleMass) {
		this.skeletalMuscleMass = skeletalMuscleMass;
	}
	public float getIdealWeight() {
		return idealWeight;
	}
	public void setIdealWeight(float idealWeight) {
		this.idealWeight = idealWeight;
	}
	public float getWeightControl() {
		return weightControl;
	}
	public void setWeightControl(float weightControl) {
		this.weightControl = weightControl;
	}
	public float getFatControl() {
		return fatControl;
	}
	public void setFatControl(float fatControl) {
		this.fatControl = fatControl;
	}
	public float getMuscleControl() {
		return muscleControl;
	}
	public void setMuscleControl(float muscleControl) {
		this.muscleControl = muscleControl;
	}
	public float getBasalMetabolicRate() {
		return basalMetabolicRate;
	}
	public void setBasalMetabolicRate(float basalMetabolicRate) {
		this.basalMetabolicRate = basalMetabolicRate;
	}
	public float getRecommendedIntakeCalories() {
		return recommendedIntakeCalories;
	}
	public void setRecommendedIntakeCalories(float recommendedIntakeCalories) {
		this.recommendedIntakeCalories = recommendedIntakeCalories;
	}
	public String getLeftArmMuscle() {
		return leftArmMuscle;
	}
	public void setLeftArmMuscle(String leftArmMuscle) {
		this.leftArmMuscle = leftArmMuscle;
	}
	public String getRightArmMuscle() {
		return rightArmMuscle;
	}
	public void setRightArmMuscle(String rightArmMuscle) {
		this.rightArmMuscle = rightArmMuscle;
	}
	public String getTrunkMuscle() {
		return trunkMuscle;
	}
	public void setTrunkMuscle(String trunkMuscle) {
		this.trunkMuscle = trunkMuscle;
	}
	public String getLeftLegMuscle() {
		return leftLegMuscle;
	}
	public void setLeftLegMuscle(String leftLegMuscle) {
		this.leftLegMuscle = leftLegMuscle;
	}
	public String getRightLegMuscle() {
		return rightLegMuscle;
	}
	public void setRightLegMuscle(String rightLegMuscle) {
		this.rightLegMuscle = rightLegMuscle;
	}
	public String getLeftArmFat() {
		return leftArmFat;
	}
	public void setLeftArmFat(String leftArmFat) {
		this.leftArmFat = leftArmFat;
	}
	public String getRightArmFat() {
		return rightArmFat;
	}
	public void setRightArmFat(String rightArmFat) {
		this.rightArmFat = rightArmFat;
	}
	public String getTrunkFat() {
		return trunkFat;
	}
	public void setTrunkFat(String trunkFat) {
		this.trunkFat = trunkFat;
	}
	public String getLeftLegFat() {
		return leftLegFat;
	}
	public void setLeftLegFat(String leftLegFat) {
		this.leftLegFat = leftLegFat;
	}
	public String getRightLegFat() {
		return rightLegFat;
	}
	public void setRightLegFat(String rightLegFat) {
		this.rightLegFat = rightLegFat;
	}
	
	
	

}
