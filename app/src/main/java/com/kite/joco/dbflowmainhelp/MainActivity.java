package com.kite.joco.dbflowmainhelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kite.joco.dbflowmainhelp.db.Ant;
import com.kite.joco.dbflowmainhelp.db.AntNoContainer;
import com.kite.joco.dbflowmainhelp.db.Colony;
import com.kite.joco.dbflowmainhelp.db.Queen;
import com.kite.joco.dbflowmainhelp.db.QueenNoContainer;
import com.kite.joco.dbflowmainhelp.db.QueenNoContainer$Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.builder.Condition;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final String LOGTAG = "DBFEXAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Colony c = new Colony();
        c.setName("First colony with container");
        c.save();

        Queen q = new Queen();
        q.setName("Elizabeth");
        q.setColony(c);
        q.save();

        Ant a = new Ant();
        a.setIsMale(true);
        a.setType("worker");
        a.goodassociateQueen(q);
        a.save();

        // I will never seen with connection of the queen
        Ant a1 = new Ant();
        a1.setIsMale(true);
        a1.setType("soldier");
        a1.originalassociateQueen(q);
        a1.save();

        Log.i(LOGTAG," First colony: " + c.getName());

        List<Ant> antList = new Select().from(Ant.class).queryList();

        for (Ant aitem : antList) {
            String gender = (aitem.isMale()) ? " Male" : " Girl";
            Log.i(LOGTAG, " This ant is " + gender + " type " + aitem.getType() + " . ");
            Log.i(LOGTAG, " My queen is (1) " + aitem.getMyQueen().getName());
            //Log.i(LOGTAG, " My queen is (2) " + aitem.getMyQueen2().getName()); turned off because of exception
            //Log.i(LOGTAG, " My queen is (3) " + aitem.getMyQueen3().getName()); turned off because of exception
        }

        List<Queen> queenList = new Select().from(Queen.class).queryList();
        for (Queen queen : queenList) {
            Log.i(LOGTAG, " This queen's name :  " + queen.getName() + " Her ants : ");
            List<Ant> ants = queen.getMyAnts();
            for (Ant myant : ants) {
                Log.i(LOGTAG, "Ant is :" + myant.getType());
            }
        }


        q.delete();
        c.delete();
        a.delete();
        a1.delete();

        Colony c2 = new Colony();
        c2.setName("Colony 2 without container");
        c2.save();

        QueenNoContainer queenNoContainer = new QueenNoContainer();
        queenNoContainer.setName("Caroline");
        queenNoContainer.save();

        AntNoContainer antNoContainer1 = new AntNoContainer();
        antNoContainer1.setMale(true);
        antNoContainer1.setType("worker");
        antNoContainer1.setQueen_id(queenNoContainer.getId());
        antNoContainer1.save();

        AntNoContainer antNoContainer2 = new AntNoContainer();
        antNoContainer2.setMale(false);
        antNoContainer2.setType("warrior");
        antNoContainer2.setQueen_id(queenNoContainer.getId());
        antNoContainer2.save();

        Log.i(LOGTAG," Second colony: " + c2.getName());

        List<AntNoContainer> antnocList = new Select().from(AntNoContainer.class).queryList();

        for (AntNoContainer aitem : antnocList) {
            String gender = (aitem.isMale()) ? " Male" : " Girl";
            Log.i(LOGTAG, " This ant is " + gender + " type " + aitem.getType() + " . ");
            long myqueenid = aitem.getQueen_id();
            QueenNoContainer myqueen = new Select().from(QueenNoContainer.class).where(Condition.column(QueenNoContainer$Table.ID).eq(myqueenid)).querySingle();
            Log.i(LOGTAG, " My queen is (1) " + myqueen.getName());
        }

        List<QueenNoContainer> queennocList = new Select().from(QueenNoContainer.class).queryList();
        for (QueenNoContainer queen : queennocList) {
            Log.i(LOGTAG, " This queen's name :  " + queen.getName() + " Her ants : ");
            List<AntNoContainer> ants = queen.getMyAnts();
            for (AntNoContainer myant : ants) {
                Log.i(LOGTAG, "Ant is :" + myant.getType());
            }
        }
        c2.delete();
        queenNoContainer.delete();
        antNoContainer1.delete();
        antNoContainer2.delete();

    }


}
