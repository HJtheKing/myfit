package com.chj.myfit.model.dto;

public class Diet {
    private int dietId;
    
    private int memberId;
    
    private String purpose;
    
    private String allergie;
    
    private String pastMeal;
    
    private String foodList;
    
    private int calories;
    
    private String mealTime;
    
	public Diet(int memberId, String purpose, String allergie, String pastMeal) {
		this.memberId = memberId;
		this.purpose = purpose;
		this.allergie = allergie;
		this.pastMeal = pastMeal;
	}

	public int getDietId() {
		return dietId;
	}
	
	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAllergie() {
		return allergie;
	}

	public void setAllergie(String allergie) {
		this.allergie = allergie;
	}

	public String getPastMeal() {
		return pastMeal;
	}

	public void setPastMeal(String pastMeal) {
		this.pastMeal = pastMeal;
	}

	public String getFoodList() {
		return foodList;
	}

	public void setFoodList(String foodList) {
		this.foodList = foodList;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

	@Override
	public String toString() {
		return "Diet [dietId=" + dietId + ", memberId=" + memberId + ", purpose=" + purpose + ", allergie=" + allergie
				+ ", pastMeal=" + pastMeal + ", foodList=" + foodList + ", calories=" + calories + ", mealTime="
				+ mealTime + "]";
	}
}
