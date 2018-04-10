public class ExceptionColBadIndex extends Exception {
    @Override
    public String getMessage() {
        return "Les indices ne sont pas bon (ex: indices negatif, hors de la colonnes...)";
    }
}
