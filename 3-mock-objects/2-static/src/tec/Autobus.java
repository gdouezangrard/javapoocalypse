package tec;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Autobus {
    private List<PassagerStandard> passagers;
    private List<PassagerStandard> descendant;
    private int passagersAssis;
    private int passagersDebout;
    private JaugeNaturel assis;
    private JaugeNaturel debout;
    private int arret;
    private int numeroArret;

    public Autobus(int assis, int debout) {
        this.assis = new JaugeNaturel(assis, 0);
        this.debout = new JaugeNaturel(debout, 0);
        this.numeroArret = 0;
        this.passagersDebout = 0;
        this.passagersAssis = 0;

        passagers = new ArrayList<PassagerStandard>();
        descendant = new ArrayList<PassagerStandard>();
    }

    public boolean aPlaceAssise() {
        return assis.estVert();
    }

    public boolean aPlaceDebout() {
        return debout.estVert();
    }

    public void demanderPlaceAssise(PassagerStandard p) {
        if (aPlaceAssise() && p.estDehors()) {
            p.accepterPlaceAssise();
            assis.incrementer();
            passagersAssis++;
            passagers.add(p);
        }
    }

    public void demanderPlaceDebout(PassagerStandard p) {
        if (aPlaceDebout() && p.estDehors()) {
            p.accepterPlaceDebout();
            debout.incrementer();
            passagersDebout++;
            passagers.add(p);
        }
    }

    public void demanderSortie(PassagerStandard p) {
        if (p.estAssis()) {
            assis.decrementer();
            passagersAssis--;
        }
        else if (p.estDebout()) {
            debout.decrementer();
            passagersDebout--;
        }

        p.accepterSortie();
        passagers.remove(p);
        descendant.add(p);
    }

    public void demanderChangerEnDebout(PassagerStandard p) {
        if (p.estAssis() && aPlaceDebout()) {
            assis.decrementer();
            passagersAssis--;
            debout.incrementer();
            passagersDebout++;
            p.accepterPlaceDebout();
        }
    }

    public void demanderChangerEnAssis(PassagerStandard p) {
        if (p.estDebout() && aPlaceAssise()) {
            debout.decrementer();
            passagersDebout--;
            assis.incrementer();
            passagersDebout++;
            p.accepterPlaceAssise();
        }
    }

    public void allerArretSuivant() {
        numeroArret++;
        for (PassagerStandard i : passagers) {
            i.nouvelArret(this, numeroArret);
        }
        for (PassagerStandard d : descendant) {
            passagers.remove(d);
        } 
        descendant.clear();
    }

    public String toString() {
        return ("[arret:" + numeroArret +
                ", assis:" + passagersAssis +
                ", debout:" + passagersDebout + "]");
    }
}
