package com.kite.joco.dbflowmainhelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kite.joco.dbflowmainhelp.db.Ant;
import com.kite.joco.dbflowmainhelp.db.Colony;
import com.kite.joco.dbflowmainhelp.db.Queen;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String LOGTAG = "DBFEXAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Colony c = new Colony();
        c.setName("First colony");
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


        List<Ant> antList = new Select().from(Ant.class).queryList();

        for (Ant aitem : antList) {
            String gender = (aitem.isMale()) ? " Male" : " Girl";
            Log.i(LOGTAG, " This ant is " + gender + " type " + aitem.getType() + " . ");
            Log.i(LOGTAG, " My queen is (1) " + aitem.getMyQueen().getName());
            //Log.i(LOGTAG, " My queen is (2) " + aitem.getMyQueen2().getName()); turned off because of exception
            Log.i(LOGTAG, " My queen is (3) " + aitem.getMyQueen3().getName());
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

    }


}
