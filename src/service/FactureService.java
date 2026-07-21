package service;

import entities.Commande;
import entities.Facture;
import repository.FactureRepository;
import java.util.ArrayList;
import java.util.List;

public class FactureService {

    private FactureRepository factureRepository = new FactureRepository();

    /**
     * Génère une facture, sauf si la commande n'est pas validée
     * ou si le numéro de facture existe déjà (doublon).
     */
    public Facture genererFacture(Commande commande, String numero) {
        if (!commande.isValidee()) {
            return null;
        }
        if (factureRepository.existeParNumero(numero)) {
            return null;
        }
        Facture facture = new Facture(numero, commande);
        return factureRepository.ajouter(facture);
    }

    public List<Facture> listerFactures() {
        return factureRepository.getTous();
    }

    public Facture trouverParId(int id) {
        return factureRepository.trouverParId(id);
    }

    public Facture trouverParCommande(Commande commande) {
        return factureRepository.trouverParCommande(commande);
    }

    public List<Facture> listerFacturesImpayeesOuPartielles() {
        List<Facture> resultat = new ArrayList<>();
        for (Facture facture : listerFactures()) {
            if (!facture.estSoldee()) {
                resultat.add(facture);
            }
        }
        return resultat;
    }
}