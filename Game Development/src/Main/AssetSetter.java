package Main;

import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // A helper method to set a slightly smaller collision box for any object
    private void setCollisionBox(SuperObject obj, int marginX, int marginY) {
        obj.solidArea = new java.awt.Rectangle(
            marginX, 
            marginY, 
            obj.width - marginX * 2, 
            obj.height - marginY * 2
        );
        obj.collision = true; // enable collision
    }

    public void setObjects() {

        int defaultMargin = 2; // margin for all objects (adjustable)

        // --- Doctor Room ---
        gp.obj[0] = new Obj_DocCarpet();
        gp.obj[0].worldX = 10 * gp.tileSize;
        gp.obj[0].worldY = 19 * gp.tileSize;
        setCollisionBox(gp.obj[0], defaultMargin, defaultMargin);

        gp.obj[1] = new Obj_bed();
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 18 * gp.tileSize;
        setCollisionBox(gp.obj[1], defaultMargin, defaultMargin);

        gp.obj[2] = new Obj_CouchSet();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;
        setCollisionBox(gp.obj[2], defaultMargin, defaultMargin);

        gp.obj[3] = new Obj_TableSet();
        gp.obj[3].worldX = 1 * gp.tileSize;
        gp.obj[3].worldY = 21 * gp.tileSize;
        setCollisionBox(gp.obj[3], defaultMargin, defaultMargin);

        gp.obj[4] = new Obj_DocCabLeft();
        gp.obj[4].worldX = 1 * gp.tileSize;
        gp.obj[4].worldY = 17 * gp.tileSize;
        setCollisionBox(gp.obj[4], defaultMargin, defaultMargin);

        gp.obj[5] = new Obj_docPictureFrame();
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 17 * gp.tileSize;
        setCollisionBox(gp.obj[5], defaultMargin, defaultMargin);

        gp.obj[6] = new Obj_Docplant();
        gp.obj[6].worldX = 8 * gp.tileSize;
        gp.obj[6].worldY = 18 * gp.tileSize;
        setCollisionBox(gp.obj[6], defaultMargin, defaultMargin);

        // --- Nurse Room ---
        gp.obj[7] = new Obj_NurseCab2();
        gp.obj[7].worldX = 27 * gp.tileSize;
        gp.obj[7].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[7], defaultMargin, defaultMargin);

        gp.obj[8] = new Obj_MiniTable();
        gp.obj[8].worldX = 24 * gp.tileSize;
        gp.obj[8].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[8], defaultMargin, defaultMargin);

        gp.obj[10] = new Obj_NCabinet();
        gp.obj[10].worldX = 32 * gp.tileSize;
        gp.obj[10].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[10], defaultMargin, defaultMargin);

        gp.obj[11] = new Obj_NurseBed();
        gp.obj[11].worldX = 36 * gp.tileSize;
        gp.obj[11].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[11], defaultMargin, defaultMargin);

        gp.obj[12] = new Obj_deco2();
        gp.obj[12].worldX = 35 * gp.tileSize;
        gp.obj[12].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[12], defaultMargin, defaultMargin);

        gp.obj[13] = new Obj_NurseTable();
        gp.obj[13].worldX = 29 * gp.tileSize;
        gp.obj[13].worldY = 37 * gp.tileSize;
        setCollisionBox(gp.obj[13], defaultMargin, defaultMargin);

        // --- Police Room ---
        gp.obj[15] = new obj_PoliceBed();
        gp.obj[15].worldX = 4 * gp.tileSize;
        gp.obj[15].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[15], defaultMargin, defaultMargin);

        gp.obj[16] = new Obj_PoliceCabinet();
        gp.obj[16].worldX = 1 * gp.tileSize;
        gp.obj[16].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[16], defaultMargin, defaultMargin);

        gp.obj[17] = new Obj_PoliceCouch();
        gp.obj[17].worldX = 11 * gp.tileSize;
        gp.obj[17].worldY = 37 * gp.tileSize;
        setCollisionBox(gp.obj[17], defaultMargin, defaultMargin);

        gp.obj[18] = new Obj_PoliceMirror();
        gp.obj[18].worldX = 13 * gp.tileSize;
        gp.obj[18].worldY = 33 * gp.tileSize;
        setCollisionBox(gp.obj[18], defaultMargin, defaultMargin);

        // --- Bathrooms ---
        gp.obj[19] = new Obj_Bath1();
        gp.obj[19].worldX = 1 * gp.tileSize;
        gp.obj[19].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[19], defaultMargin, defaultMargin);

        gp.obj[20] = new Obj_Bath2();
        gp.obj[20].worldX = 9 * gp.tileSize;
        gp.obj[20].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[20], defaultMargin, defaultMargin);

        gp.obj[21] = new Obj_Bath3();
        gp.obj[21].worldX = 17 * gp.tileSize;
        gp.obj[21].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[21], defaultMargin, defaultMargin);

        // --- Teacher Room ---
        gp.obj[22] = new Obj_Teacherbed();
        gp.obj[22].worldX = 23 * gp.tileSize;
        gp.obj[22].worldY = 17 * gp.tileSize;
        setCollisionBox(gp.obj[22], defaultMargin, defaultMargin);

        gp.obj[23] = new Obj_TeacherCabinet();
        gp.obj[23].worldX = 29 * gp.tileSize;
        gp.obj[23].worldY = 17 * gp.tileSize;
        setCollisionBox(gp.obj[23], defaultMargin, defaultMargin);

        gp.obj[24] = new Obj_TBookshelves();
        gp.obj[24].worldX = 36 * gp.tileSize;
        gp.obj[24].worldY = 13 * gp.tileSize;
        setCollisionBox(gp.obj[24], defaultMargin, defaultMargin);

        gp.obj[25] = new Obj_TeacherTable();
        gp.obj[25].worldX = 38 * gp.tileSize;
        gp.obj[25].worldY = 17 * gp.tileSize;
        setCollisionBox(gp.obj[25], defaultMargin, defaultMargin);

        gp.obj[26] = new Obj_NurseTv();
        gp.obj[26].worldX = 30 * gp.tileSize;
        gp.obj[26].worldY = 34 * gp.tileSize;
        setCollisionBox(gp.obj[26], defaultMargin, defaultMargin);

        gp.obj[27] = new Obj_Pbed();
        gp.obj[27].worldX = 1 * gp.tileSize;
        gp.obj[27].worldY = 42 * gp.tileSize;
        setCollisionBox(gp.obj[27], defaultMargin, defaultMargin);

        gp.obj[28] = new Obj_Pcloset();
        gp.obj[28].worldX = 6 * gp.tileSize;
        gp.obj[28].worldY = 42 * gp.tileSize;
        setCollisionBox(gp.obj[28], defaultMargin, defaultMargin);

        gp.obj[29] = new Obj_Ptable();
        gp.obj[29].worldX = 10 * gp.tileSize;
        gp.obj[29].worldY = 48 * gp.tileSize;
        setCollisionBox(gp.obj[29], defaultMargin, defaultMargin);
        
        gp.obj[30] = new Obj_Scabinet();
        gp.obj[30].worldX = 49 * gp.tileSize;
        gp.obj[30].worldY = 4 * gp.tileSize;
        setCollisionBox(gp.obj[30], defaultMargin, defaultMargin);
        
        gp.obj[31] = new Obj_Scab2();
        gp.obj[31].worldX = 36 * gp.tileSize;
        gp.obj[31].worldY = 1 * gp.tileSize;
        setCollisionBox(gp.obj[31], defaultMargin, defaultMargin);
        
        gp.obj[32] = new Obj_Stable();
        gp.obj[32].worldX = 36 * gp.tileSize;
        gp.obj[32].worldY = 9 * gp.tileSize;
        setCollisionBox(gp.obj[32], defaultMargin, defaultMargin);
        
        gp.obj[33] = new Obj_Stable2();
        gp.obj[33].worldX = 42 * gp.tileSize;
        gp.obj[33].worldY = 6 * gp.tileSize;
        setCollisionBox(gp.obj[33], defaultMargin, defaultMargin);
        
        gp.obj[34] = new Obj_VictimBed();
        gp.obj[34].worldX = 36 * gp.tileSize;
        gp.obj[34].worldY = 42 * gp.tileSize;
        setCollisionBox(gp.obj[34], defaultMargin, defaultMargin);
        
        gp.obj[35] = new Obj_NurseTv();
        gp.obj[35].worldX = 34 * gp.tileSize;
        gp.obj[35].worldY = 43 * gp.tileSize;
        setCollisionBox(gp.obj[35], defaultMargin, defaultMargin);
        
        gp.obj[36] = new Obj_VictimCab();
        gp.obj[36].worldX = 23 * gp.tileSize;
        gp.obj[36].worldY = 42 * gp.tileSize;
        setCollisionBox(gp.obj[36], defaultMargin, defaultMargin);
        
        gp.obj[37] = new Obj_VictimChair();
        gp.obj[37].worldX = 29 * gp.tileSize;
        gp.obj[37].worldY = 46 * gp.tileSize;
        setCollisionBox(gp.obj[37], defaultMargin, defaultMargin);
        
        gp.obj[38] = new Obj_VacantBed();
        gp.obj[38].worldX = 51 * gp.tileSize;
        gp.obj[38].worldY = 12 * gp.tileSize;
        setCollisionBox(gp.obj[38], defaultMargin, defaultMargin);
        
        gp.obj[39] = new Obj_Vacantcab();
        gp.obj[39].worldX = 45 * gp.tileSize;
        gp.obj[39].worldY = 12 * gp.tileSize;
        setCollisionBox(gp.obj[39], defaultMargin, defaultMargin);
        
        gp.obj[40] = new Obj_CouchSet();
        gp.obj[40].worldX = 52 * gp.tileSize;
        gp.obj[40].worldY = 22 * gp.tileSize;
        setCollisionBox(gp.obj[40], defaultMargin, defaultMargin);
        
        gp.obj[41] = new Obj_Ptable();
        gp.obj[41].worldX = 45 * gp.tileSize;
        gp.obj[41].worldY = 18 * gp.tileSize;
        setCollisionBox(gp.obj[41], defaultMargin, defaultMargin);
        
        gp.obj[42] = new Obj_CrSink();
        gp.obj[42].worldX = 5 * gp.tileSize;
        gp.obj[42].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[42], defaultMargin, defaultMargin);
        
        gp.obj[43] = new Obj_CrSink();
        gp.obj[43].worldX = 13 * gp.tileSize;
        gp.obj[43].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[43], defaultMargin, defaultMargin);
        
        gp.obj[44] = new Obj_CrSink();
        gp.obj[44].worldX = 21 * gp.tileSize;
        gp.obj[44].worldY = 2 * gp.tileSize;
        setCollisionBox(gp.obj[44], defaultMargin, defaultMargin);
    }
    
}
