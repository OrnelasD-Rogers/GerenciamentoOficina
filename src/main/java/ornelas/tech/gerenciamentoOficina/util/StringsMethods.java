package ornelas.tech.gerenciamentoOficina.util;

public class StringsMethods {

    /**
     *
     * @param val String a ter a primeira letra transformada em maiuscula
     * @return String transformada
     */
    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    /**
     *
     * @param val String a ter a primeira letra de cada palavra transformada em maiuscula
     * @return String transformada
     */
    public static String upperCaseAllFirstWord(String val) {
        String[] array = val.split(" ");
        String alteredString = "";
        for (String word: array) {
            alteredString += " " + upperCaseFirst(word);
        }

        return alteredString.trim();
    }
}
