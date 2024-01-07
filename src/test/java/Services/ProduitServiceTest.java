package Services;

import org.gproduit.Produit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

}