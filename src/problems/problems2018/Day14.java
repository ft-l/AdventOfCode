package problems.problems2018;

import problems.Day;

import java.util.Arrays;

public class Day14 extends Day {

    public Day14() {
        super(14, 2018);
    }

    public static final String TEST_INPUT_1 = "2018";
    public static final String TEST_INPUT_2 = "92510";

    @Override
    public void part1() {
        int recipesToPass = Integer.parseInt(input.replaceAll("\n", ""));
        int[] recipes = new int[recipesToPass+10];

        recipes[0] = 3;
        recipes[1] = 7;

        int recipeListLength = 2;

        int elf1 = 0;
        int elf2 = 1;

        while (recipeListLength < recipes.length) {
            String newRecipes = ""+(recipes[elf1]+recipes[elf2]);
            if (newRecipes.length() > 1) {
                recipes[recipeListLength] = Integer.parseInt("" + newRecipes.charAt(0));
                recipes[recipeListLength + 1] = Integer.parseInt("" + newRecipes.charAt(1));
                recipeListLength += 2;
            } else {
                recipes[recipeListLength] = Integer.parseInt(newRecipes);
                recipeListLength += 1;
            }
            elf1 = (elf1 + recipes[elf1] + 1) % recipeListLength;
            elf2 = (elf2 + recipes[elf2] + 1) % recipeListLength;
        }

        String lastTen = "";

        for (int i = recipes.length-10; i < recipes.length; i++) {
            lastTen += recipes[i];
        }

        System.out.println(lastTen);
    }

    @Override
    public void part2() {
        String patternToFind = input.replaceAll("\n", "");
        int[] recipes = new int[30000000];

        recipes[0] = 3;
        recipes[1] = 7;

        int recipeListLength = 2;

        int elf1 = 0;
        int elf2 = 1;

        String lastPattern = "";

        while (!lastPattern.equals(patternToFind)) {
            String newRecipes = ""+(recipes[elf1]+recipes[elf2]);
            if (newRecipes.length() > 1) {
                recipes[recipeListLength] = Integer.parseInt("" + newRecipes.charAt(0));
                recipeListLength += 1;

                lastPattern = "";

                if (recipeListLength >= (patternToFind).length()) {
                    for (int i = recipeListLength - (patternToFind).length(); i < recipeListLength; i++) {
                        lastPattern += recipes[i];
                    }
                }

                if (lastPattern.equals(patternToFind)) {
                    break;
                }

                recipes[recipeListLength] = Integer.parseInt("" + newRecipes.charAt(1));
                recipeListLength += 1;
            } else {
                recipes[recipeListLength] = Integer.parseInt(newRecipes);
                recipeListLength += 1;
            }
            elf1 = (elf1 + recipes[elf1] + 1) % recipeListLength;
            elf2 = (elf2 + recipes[elf2] + 1) % recipeListLength;

            lastPattern = "";

            if (recipeListLength >= (patternToFind).length()) {
                for (int i = recipeListLength - (patternToFind).length(); i < recipeListLength; i++) {
                    lastPattern += recipes[i];
                }
            }
        }
        System.out.println(recipeListLength - (patternToFind).length());
    }
}
