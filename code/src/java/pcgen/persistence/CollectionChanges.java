package pcgen.persistence;

import java.util.Collection;

import pcgen.cdom.base.AssociatedPrereqObject;

public class CollectionChanges<T> implements Changes<T>
{
	private Collection<T> positive;
	private Collection<T> negative;
	private boolean clear;

	public CollectionChanges(Collection<T> added, Collection<T> removed,
		boolean globallyCleared)
	{
		positive = added;
		negative = removed;
		clear = globallyCleared;
	}

	public boolean includesGlobalClear()
	{
		return clear;
	}

	public boolean isEmpty()
	{
		return !clear && !hasAddedItems() && !hasRemovedItems();
	}

	public Collection<T> getAdded()
	{
		return positive;
	}

	public boolean hasAddedItems()
	{
		return positive != null && !positive.isEmpty();
	}

	public Collection<T> getRemoved()
	{
		return negative;
	}

	public boolean hasRemovedItems()
	{
		return negative != null && !negative.isEmpty();
	}

	public AssociatedPrereqObject getAddedAssociation(T added)
	{
		return null;
	}

	public AssociatedPrereqObject getRemovedAssociation(T removed)
	{
		return null;
	}
}
