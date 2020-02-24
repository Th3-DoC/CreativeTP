package me.th3doc.creativetp.enums;

public enum Perms {

    _creativeTpAll("creativetp.*"),
    _tpcCommand("creativetp.tp.command"),
    _tpcAll("creativetp.tp.*"),
    _tpcDelay("creativetp.tp.delay"),
    _tpcNorm("creativetp.tp.normal"),
    _tpcVoid("creativetp.tp.void"),
    _worldMod("creativetp.world.*"),
    _setWorld("creativetp.world.set"),
    _createWorld("creativetp.world.create"),
    _gmBypass("creativetp.world.gm"),
    _permBypass("creativetp.world.perm"),
    _reloadConfig("creativetp.world.reload");

    public String perm;

    Perms(String perm) {
        this.perm = perm;
    }
}
