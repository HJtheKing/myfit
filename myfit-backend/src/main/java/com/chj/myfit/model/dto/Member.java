package com.chj.myfit.model.dto;

import java.time.LocalDate;
import java.util.List;

public class Member {
    private int memberId;
    
    private String email;
    
    private String password;
    
    private String alternateEmail;
    
    private String name;
    
    private LocalDate birthDate;
    
    private String phone;
    
    private LocalDate signupDate;
    
    private String role;
    
    private List<Inbody> inbodies;
    
    private boolean postAlert = false;
    
    private boolean promotionAlert = false;
    
    
    @Override
	public String toString() {
		return "Member [memberId=" + memberId + ", email=" + email + ", password=" + password + ", alternateEmail="
				+ alternateEmail + ", name=" + name + ", birthDate=" + birthDate + ", phone=" + phone + ", signupDate="
				+ signupDate + ", role=" + role + ", inbodies=" + inbodies + ", postAlert=" + postAlert
				+ ", promotionAlert=" + promotionAlert + "]";
	}

	public boolean isPostAlert() {
		return postAlert;
	}

	public void setPostAlert(boolean postAlert) {
		this.postAlert = postAlert;
	}

	public boolean isPromotionAlert() {
		return promotionAlert;
	}

	public void setPromotionAlert(boolean promotionAlert) {
		this.promotionAlert = promotionAlert;
	}

	public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(LocalDate signupDate) {
        this.signupDate = signupDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Inbody> getInbodies() {
        return inbodies;
    }

    public void setInbodies(List<Inbody> inbodies) {
        this.inbodies = inbodies;
    }
}
