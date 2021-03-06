package com.massivecraft.massivetickets.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.store.SenderColl;
import com.massivecraft.massivetickets.Const;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.predictate.IsCurrentlyWorkingPredictate;
import com.massivecraft.massivetickets.predictate.IsTicketPredictate;

public class MPlayerColl extends SenderColl<MPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MPlayerColl i = new MPlayerColl();
	public static MPlayerColl get() { return i; }
	private MPlayerColl()
	{
		super(Const.COLLECTION_BASENAME_MPLAYER, MPlayer.class, MStore.getDb(), MassiveTickets.get());
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public Collection<MPlayer> getAllCurrentlyWorking()
	{
		return this.getAll(IsCurrentlyWorkingPredictate.get());
	}
	
	public Collection<MPlayer> getAllTickets()
	{
		return this.getAll(IsTicketPredictate.get());
	}
	
	// For the list command
	public List<String> getAllTicketListLines(Object watcherObject)
	{
		List<String> ret = new ArrayList<String>();
		for (MPlayer ticket : this.getAllTickets())
		{
			ret.add(ticket.getListLine(watcherObject));
		}
		return ret;
	}
	
}
