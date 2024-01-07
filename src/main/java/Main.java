import Services.ProduitService;
import org.gproduit.Produit;

public class Main {
    public static void main(String[] args) {
        ProduitService service = new ProduitService();

        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
        service.ajouterProduit(produit1);
        System.out.println("Produit ajoute : " + produit1);

        service.supprimerProduit(1L);
        System.out.println("Produit supprimer");
    }
}