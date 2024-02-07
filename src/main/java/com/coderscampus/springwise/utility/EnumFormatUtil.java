package com.coderscampus.springwise.utility;

public class EnumFormatUtil {

    // Private constructor to prevent instantiation
    private EnumFormatUtil() {}

    // Static method to format enum constants into a more readable format
    public static String formatEnumName(Enum<?> enumConstant) {
        if (enumConstant == null) {
            return "null";
        }

        // Check if the enum name is exactly "CRUD", return it as is
        if ("CRUD".equals(enumConstant.name())) {
            return "CRUD";
        }

        String formattedName = enumConstant.name().toLowerCase().replace('_', ' ');
        String[] words = formattedName.split("\\s");
        StringBuilder formattedNameBuilder = new StringBuilder();

        for (String word : words) {
            // Check each word to see if it is CRUD, if so, leave it capitalized
            if ("crud".equalsIgnoreCase(word)) {
                formattedNameBuilder.append("CRUD").append(" ");
            } else {
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                formattedNameBuilder.append(capitalizedWord).append(" ");
            }
        }

        return formattedNameBuilder.toString().trim();
    }
}