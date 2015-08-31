package com.kite.joco.dbflowmainhelp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

/**
 * Created by Joco on 2015.08.31..
 */
@Table(databaseName = ColonyDatabase.NAME)
public class Ant extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String type;

    @Column
    boolean isMale;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "queen_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Queen> queenModelContainer;

    // it is get null
    public Queen getMyQueen(){
        this.queenModelContainer = new ForeignKeyContainer<Queen>(Queen.class);
        return this.queenModelContainer.toModel();
    }

    // It is throw nullpointerexception
    public Queen getMyQueen2() {
        this.queenModelContainer = new ForeignKeyContainer<Queen>(Queen.class);
        long l = (long) this.queenModelContainer.getValue(Queen$Table.ID);
        Queen findqueen = new Select().from(Queen.class).where(Condition.column(Queen$Table.ID).eq(l)).querySingle();
        return findqueen;
    }

    // It is throw nullpointerexception
    public Queen getMyQueen3(){
        this.queenModelContainer = new ForeignKeyContainer<Queen>(Queen.class);
        return this.queenModelContainer.load();
    }

    // I can't see other ways to reach my ant's queen !

    /**
     * Example of setting the model for the queen.
     */
    public void originalassociateQueen(Queen queen) {
        queenModelContainer = new ForeignKeyContainer<>(Queen.class);
        queenModelContainer.setModel(queen);
    }

    public void goodassociateQueen(Queen queen) {
        queenModelContainer = new ForeignKeyContainer<>(Queen.class);
        queenModelContainer.put(Queen$Table.ID, queen.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}