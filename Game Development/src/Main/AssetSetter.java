package Main;

import object.*;
import Entity.*;

public class AssetSetter {


GamePanel gp;

public AssetSetter(GamePanel gp) { 
    this.gp = gp;
}

// --- Helper to set collision box ---
private void setCollisionBox(SuperObject obj, int marginX, int marginY, boolean collision) {
    if (obj == null) return;
    obj.solidArea = new java.awt.Rectangle(
        marginX, 
        marginY, 
        obj.width - marginX * 2, 
        obj.height - marginY * 2
    );
    obj.collision = collision;
}

// Overload for default collision enabled
private void setCollisionBox(SuperObject obj, int marginX, int marginY) {
    setCollisionBox(obj, marginX, marginY, true);
}

// --- Set up all objects ---
public void setObjects() {
    int defaultMargin = 3; // default collision margin

    // Doctor Room objects
    createObject(0, new Obj_DocCarpet(), 10, 19, defaultMargin);
    createObject(1, new Obj_bed(), 10, 18, defaultMargin);
    createObject(2, new Obj_CouchSet(), 10, 22, defaultMargin);
    createObject(3, new Obj_TableSet(), 1, 21, defaultMargin);
    createObject(4, new Obj_DocCabLeft(), 1, 17, defaultMargin);
    createObject(5, new Obj_docPictureFrame(), 11, 17, defaultMargin);
    createObject(6, new Obj_Docplant(), 8, 18, defaultMargin);

    // Nurse Room
    createObject(7, new Obj_NurseCab2(), 27, 33, defaultMargin);
    createObject(8, new Obj_MiniTable(), 24, 33, defaultMargin);
    createObject(10, new Obj_NCabinet(), 32, 33, defaultMargin);
    createObject(11, new Obj_NurseBed(), 36, 33, defaultMargin);
    createObject(12, new Obj_deco2(), 35, 33, defaultMargin);
    createObject(13, new Obj_NurseTable(), 29, 37, defaultMargin);
    createObject(26, new Obj_NurseTv(), 30, 34, defaultMargin);
    createObject(35, new Obj_NurseTv(), 34, 43, defaultMargin);

    // Police Room
    createObject(15, new obj_PoliceBed(), 4, 33, defaultMargin);
    createObject(16, new Obj_PoliceCabinet(), 1, 33, defaultMargin);
    createObject(17, new Obj_PoliceCouch(), 11, 37, defaultMargin);
    createObject(18, new Obj_PoliceMirror(), 13, 33, defaultMargin);

    // Bathrooms
    createObject(19, new Obj_Bath1(), 1, 2, defaultMargin);
    createObject(20, new Obj_Bath2(), 9, 2, defaultMargin);
    createObject(21, new Obj_Bath3(), 17, 2, defaultMargin);

    // Teacher Room
    createObject(22, new Obj_Teacherbed(), 23, 17, defaultMargin);
    createObject(23, new Obj_TeacherCabinet(), 29, 17, defaultMargin);
    createObject(24, new Obj_TBookshelves(), 36, 13, defaultMargin);
    createObject(25, new Obj_TeacherTable(), 38, 17, defaultMargin);

    //  Other objects
    createObject(27, new Obj_Pbed(), 1, 42, defaultMargin);
    createObject(28, new Obj_Pcloset(), 6, 42, defaultMargin);
    createObject(29, new Obj_Ptable(), 10, 48, defaultMargin);
    createObject(30, new Obj_Scabinet(), 36, 1, defaultMargin);
    createObject(31, new Obj_VictimBed(), 36, 42, defaultMargin);
    createObject(32, new Obj_VictimCab(), 23, 42, defaultMargin);
    createObject(33, new Obj_VictimChair(), 29, 46, defaultMargin);
    createObject(34, new Obj_VacantBed(), 51, 12, defaultMargin);
    createObject(35, new Obj_Vacantcab(), 45, 12, defaultMargin);
    createObject(36, new Obj_CouchSet(), 52, 22, defaultMargin);
    createObject(37, new Obj_Ptable(), 45, 18, defaultMargin);
    createObject(38, new Obj_CrSink(), 5, 2, defaultMargin);
    createObject(39, new Obj_CrSink(), 13, 2, defaultMargin);
    createObject(40, new Obj_CrSink(), 21, 2, defaultMargin);
    createObject(41, new Obj_TeacherTable(), 10, 56, defaultMargin);
    createObject(42, new Obj_KitchenSink(), 2, 51, defaultMargin);
    createObject(43, new Obj_Kitchen2(), 1, 51, defaultMargin);
    createObject(44, new Obj_kitchen3(), 1, 55, defaultMargin);
    createObject(45, new Obj_Kitchen4(), 8, 51, defaultMargin);
    createObject(46, new Obj_Kitchen5(), 1, 62, defaultMargin);
    createObject(47, new Obj_KitchenCabinet(), 1,64, defaultMargin);
    createObject(48, new Obj_Kitchen9(), 1, 67, defaultMargin);
    createObject(49, new Obj_KitchenTable2(), 6, 69, defaultMargin);
    createObject(50, new Obj_KitchenTable3(), 10, 74, defaultMargin);
    createObject(51, new Obj_Portrait(), 28, 61, defaultMargin);
    createObject(52, new Obj_Tv(), 37, 62, defaultMargin);
    createObject(53, new Obj_Table5(), 29, 64, defaultMargin);
    createObject(54, new Obj_Table5(), 29, 69, defaultMargin);
    createObject(55, new Obj_Table6(), 35, 74, defaultMargin);
    createObject(56, new Obj_cab(), 23, 62, defaultMargin);
    
     
}

// Helper to create objects with world coordinates and collision
public void createObject(int index, SuperObject obj, int worldCol, int worldRow, int margin) {
    if (index < 0 || index >= gp.obj.length) return;
    gp.obj[index] = obj;
    obj.worldX = worldCol * gp.tileSize;
    obj.worldY = worldRow * gp.tileSize;
    setCollisionBox(obj, margin, margin);
}

// --- Set up NPCs ---
public void setNPC() {
    // Example: add one Lady NPC at position (10,10)
    gp.npc[0] = new Npc_lady(gp);
    gp.npc[0].worldX = gp.tileSize * 10;
    gp.npc[0].worldY = gp.tileSize * 13;

    // Add more NPCs as needed
}


}
