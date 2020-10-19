package com.zpl.practice;

/**
 * @author 张沛霖
 * @date 2020/9/4
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private static int anInt;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
    }

    public static void main(String[] args) {
        new NutritionFacts.Builder(1, 1).build();
    }
}
