package tec;

import tec.PassagerAbstrait;
import tec.FauxBus;


abstract public class TestPassagerAbstrait {
    abstract protected PassagerAbstrait creerPassager(String nom, int destination);

    void testFaussaire() {
        FauxBus f = new FauxBus(5, 6);

        assert 0 == f.messages.size();

        f.demanderChangerEnDebout(null);

        assert "demanderChangerEnDebout" == f.messages.getLast();
    }

    void testEtat() {
        PassagerAbstrait passager = this.creerPassager("Goliath", 4);

        passager.accepterPlaceAssise();
        assert (passager.estAssis() == true) : "accepterPlaceAssis";

        passager.accepterPlaceDebout();
        assert (passager.estDebout() == true) : "accepterPlaceDebout";

        passager.accepterSortie();
        assert (passager.estDehors() == true) : "accepterSortie";
    }

    void testExceptionMonter() {
        class AnonymousTransport implements Transport {
            public boolean aPlaceAssise() {return true;}
            public boolean aPlaceDebout() {return true;}
            public void allerArretSuivant() {}
        }
        AnonymousTransport transport = new AnonymousTransport();
        PassagerAbstrait passager = this.creerPassager("Goliath", 3);

        try {
            passager.monterDans(transport);
            assert false : "Exception not thrown";
        } catch (TecInvalidException e) {
        }
    }

    void testMonter() throws TecInvalidException {
        FauxBus bus = new FauxBus(true, true);
        PassagerAbstrait passager = this.creerPassager("Goliath", 3);

        testChoixPlaceMontee(bus, passager);

        bus = new FauxBus(true, false);
        passager = this.creerPassager("Goliath", 3);

        testChoixPlaceMontee(bus, passager);

        bus = new FauxBus(false, true);
        passager = this.creerPassager("Goliath", 3);

        testChoixPlaceMontee(bus, passager);

        bus = new FauxBus(false, false);
        passager = this.creerPassager("Goliath", 3);

        testChoixPlaceMontee(bus, passager);
    }

    abstract void testChoixPlaceMontee(FauxBus bus, Passager passager) throws TecInvalidException;

    abstract void testChoixChangerPlace() throws TecInvalidException;

    void testArret() {
        FauxBus bus = new FauxBus(5, 10);
        PassagerAbstrait passager = this.creerPassager("Goliath", 6);

        passager.nouvelArret(bus, 6);
        assert bus.messages.getLast() == "demanderSortie";
        bus.messages.clear();

        passager.nouvelArret(bus, 9);
        assert bus.messages.getLast() == "demanderSortie";
    }

    public void lancer() throws TecInvalidException {
        System.out.println(getClass().getName());

        int nbTest = 0;

        System.out.print('.'); nbTest++;
        testFaussaire();

        System.out.print('.'); nbTest++;
        testEtat();

        System.out.print('.'); nbTest++;
        testArret();

        System.out.print('.'); nbTest++;
        testMonter();

        System.out.print('.'); nbTest++;
        testChoixChangerPlace();

        System.out.print('.'); nbTest++;
        testExceptionMonter();

        System.out.println(" OK (" + nbTest + "/5)"); 
    }
}
