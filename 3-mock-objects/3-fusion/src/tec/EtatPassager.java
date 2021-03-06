package tec;

/**
 * Cette classe represente l'etat d'un passager dans un transport en commun.
 * Un passager a trois etats possibles : DEHORS, ASSIS et DEBOUT.
 *
 * Il y a un etat à l'exterieur du transport (DEHORS) et deux etats à 
 * l'intérieur (ASSIS, DEBOUT).
 *  
 * Les instances de cette classe sont des objets constants.
 *
 * @author Georgy
 * @since 2007-2014
 **/
class EtatPassager {

  private final int DEHORS = 1;
  private final int ASSIS  = 2;
  private final int DEBOUT = 3;

  private final int courant;

  /**
   * construit une l'instance avec l'etat DEHORS du passager.
   * 
   */
  public EtatPassager() {
    courant = DEHORS;
  }

  /**
   * Construit une instance en precisant un des etats du passager.
   * 
   * @param e valeur de l'etat.
   */
  private EtatPassager(int e) {
    courant = e;
  }

  /**
   * Le passager est-il a l'exterieur du transport ?
   *
   * @return vrai si instanciation avec DEHORS;
   */
  public boolean estExterieur() {
    return courant == DEHORS;
  }

  /**
   * Le passager est-il assis a l'interieur du transport ?
   *
   * @return vrai si instanciation avec ASSIS;
   */
  public boolean estAssis() {
    return courant == ASSIS;
  }

  /**
   * Le passager est-il debout a l'interieur du transport ?
   *
   * @return vrai si instanciation avec DEBOUT;
   */
  public boolean estDebout() {
    return courant == DEBOUT;
  }

  /**
   * Le passager est-il a l'interieur du transport ?
   *
   * @return vrai si instanciation avec ASSIS ou DEBOUT.
   */
  public boolean estInterieur() {
    return courant == ASSIS || courant == DEBOUT;
  }


  /**
   * Fournit une nouvelle instance d'EtatPassager.
   *
   * @return instance dans l'etat ASSIS.
   */
  public EtatPassager assis() {
    return new EtatPassager(ASSIS); 
  }

  /**
   * Fournit une nouvelle instance d'EtatPassager.
   *
   * @return instance dans l'etat DEBOUT.
   */
  public EtatPassager debout() {
    return new EtatPassager(DEBOUT);
  }

  /**
   * Fournit une nouvelle instance d'EtatPassager.
   *
   * @return instance dans l'etat DEHORS.
   */
  public EtatPassager dehors() {
    return new EtatPassager(DEHORS);
  }

  /*
   * Cette methode est heritee de la classe {@link java.lang.Object}.
   * Tres utile pour le debogage, elle permet de fournir une 
   * chaine de caracteres correspondant a l'etat d'un objet.
   * <p> Un code par defaut est definit dans 
   * {@link java.lang.Object#toString() la classe Object} 
   * Il faut adapter (redefinir) le code de cette methode à chaque classe.
   */
  @Override
  public String toString() {
    String nom = null;
    switch(courant) {
    case DEHORS :
      nom = "dehors";
      break;
    case ASSIS :
      nom = "assis";
      break;
    case DEBOUT :
      nom = "debout";
      break;
    }
    return "<" + nom + ">";
  }
}
