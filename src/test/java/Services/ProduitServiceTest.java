package Services;

import org.gproduit.Produit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Map;
public class ProduitServiceTest {
    ProduitService service;
    @Before
    public void setUp() throws Exception {
        service = new ProduitService();
    }
    @Test
    public void testAjouterProduitValid() {
        Produit produit = new Produit(1L, "Produit1", 20.0, 5);
        service.ajouterProduit(produit);
        assertEquals(produit, service.obtenirProduit(1L));
    }

    @Test
    public void testAjouterProduitExistant() {
        service.ajouterProduit(new Produit(2L, "Produit2", 15.0, 10));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.ajouterProduit(new Produit(2L, "Produit2", 20.0, 5));
        });
        assertTrue(exception.getMessage().contains("existe déjà"));
    }

    @Test
    public void testSupprimerProduitExistant() {
        Produit produit = new Produit(9L, "Produit9", 50.0, 2);
        service.ajouterProduit(produit);
        service.supprimerProduit(9L);
        assertNull(service.obtenirProduit(9));
    }

    @Test
    public void testSupprimerProduitNonExistant() {
        try {
            service.supprimerProduit(999L);
            fail("Une RuntimeException aurait dû être levée");
        } catch (RuntimeException e) {
            assertEquals("Produit non trouvé", e.getMessage());
        }
    }

    @Test
    public void testGetProduits() {
        Produit produit1 = new Produit(10L, "Produit10", 20.0, 5);
        Produit produit2 = new Produit(20L, "Produit20", 30.0, 3);
        service.ajouterProduit(produit1);
        service.ajouterProduit(produit2);

        Map<Long, Produit> produits = service.getProduits();
        assertEquals(2, produits.size());
        assertTrue(produits.containsKey(produit1.getId()));
        assertTrue(produits.containsKey(produit2.getId()));
    }
}