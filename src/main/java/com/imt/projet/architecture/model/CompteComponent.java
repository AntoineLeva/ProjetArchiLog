
package com.imt.projet.architecture.model;

import java.util.UUID;

public interface CompteComponent {
    UUID getId();
    String getNom();
    String getEmail();
    void afficherDetails();
}
