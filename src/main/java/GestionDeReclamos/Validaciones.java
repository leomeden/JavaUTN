package GestionDeReclamos;

import static java.lang.Long.parseLong;

public class Validaciones {

    public boolean validateCUIT(String strNum){
      if (isNumeric(strNum)){
          return strNum.length() == 11;
      }
      return false;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
