import view.MenuView;

/**
 * Point d'entrée de l'application.
 * Toute la construction des services et des vues est faite par MenuView.
 */
public class Main {

    public static void main(String[] args) {
        new MenuView().demarrer();
    }
}
