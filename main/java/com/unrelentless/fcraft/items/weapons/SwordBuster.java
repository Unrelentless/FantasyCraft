package com.unrelentless.fcraft.items.weapons;

import com.unrelentless.fcraft.FantasyCraft;

public class SwordBuster extends FCraftWeapon {

	public SwordBuster(){
		super(ToolMaterial.IRON);
		setCreativeTab(FantasyCraft.fcraftTabWeapons);
		this.setUnlocalizedName("swordBuster");
		this.setFull3D();
	}
}
