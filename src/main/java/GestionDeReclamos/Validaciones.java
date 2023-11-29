package GestionDeReclamos;

import static java.lang.Long.parseLong;

public class Validaciones {

    public boolean validateCUIT(String strNum){
      if (isNumeric(strNum)){
          return strNum.length() == 11;
      }
      return false;
    }

    public boolean validateDNI(String strNum){
        if (isNumeric(strNum)){
            return (strNum.length() < 9 && strNum.length() > 6);
        }
        return false;
    }

    public boolean validateNotVoid(String str){
        return !str.isEmpty();
    }

    public boolean isNumeric(String strNum) {
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
    public boolean validateRango(int val, int max){
        return val <= max && val != 0;
    }
}
