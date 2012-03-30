package addressbook;
/*
 * @(#)OrderedLinkedList.java	1.0 10/10/20
 *
 * Source code provided by Professor Larry Page.
 * Modified by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                OrderedLinkedList
 * --------------------------------------------------
 *  (no fields)
 * --------------------------------------------------
 *  +OrderedLinkedList()
 *  +OrderedLinkedList(OrderedLinkedList)
 *  +search(DataElement): boolean
 *  +insertNode(DataElement): void
 *  +deleteNode(DataElement): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - original source code provided by professor
 */

public class OrderedLinkedList extends LinkedListClass
{
	//default constructor
	public OrderedLinkedList()
	{
		super();
	}

	//copy constructor
	public OrderedLinkedList(OrderedLinkedList otherList)
	{
		super(otherList);
	}

	//Method to determine whether searchItem is in
	//the list.
	//Postcondition: Returns true if searchItem is found
	//               in the list; false otherwise.
	public boolean search(DataElement searchItem)
	{
		LinkedListNode current; //variable to traverse the list
		boolean found;

		current = first;  //set current to point to the first
		//node in the list

		found = false;    //set found to false

		while(current != null && !found ) //search the list
			if(current.info.compareTo(searchItem) >= 0)
				found = true;
			else
				current = current.link; //make current point to
		//the next node

		if(found)
			found = current.info.equals(searchItem);

		return found;
	}

	//Method to insert insertItem in the list.
	//Postcondition: first points to the new list,
	//       insertItem is inserted at the proper place
	//       in the list, and count is incremented by 1.
	public void insertNode(DataElement insertItem)
	{
		LinkedListNode current;     //variable to traverse the list
		LinkedListNode trailCurrent;    //variable just before current
		LinkedListNode newNode;     //variable to create a node

		boolean  found;

		newNode = new LinkedListNode(); //create the node
		newNode.info = insertItem.getCopy(); //store newItem
		//in the node
		newNode.link = null;    //set the link field of the node
		//to null

		if(first == null)  //Case 1
		{
			first = newNode;
			count++;
		}
		else
		{
			trailCurrent = first;
			current = first;
			found = false;

			while(current != null && !found) //search the list
			if(current.info.compareTo(insertItem) >= 0)
				found = true;
			else
			{
				trailCurrent = current;
				current = current.link;
			}

			if(current == first)   //Case 2
			{
				newNode.link = first;
				first = newNode;
				count++;
			}
			else          //Case 3
			{
				trailCurrent.link = newNode;
				newNode.link = current;
				count++;
			}
		}//end else
	}//end insertNode

	//Method to delete deleteItem from the list.
	//Postcondition: If found, the node containing
	//               deleteItem is deleted from the
	//               list. Also, first points to the first
	//               node, last points to the last
	//               node of the updated list, and count
	//               is decremented by 1.
	public void deleteNode(DataElement deleteItem)
	{
		LinkedListNode current;      //variable to traverse the list
		LinkedListNode trailCurrent; //variable just before current
		boolean found;

		if(first == null)    //Case 1; list is empty.
			System.err.println("Cannot delete from an "
					+ "empty list.");
		else
		{
			if(first.info.equals(deleteItem)) //Case 2
			{
				first = first.link;
				count--;
			}
			else  //search the list for the node with the given info
			{
				found = false;
				trailCurrent = first;   //set trailCurrent to point to
				//the first node
				current = first.link;   //set current to point to the
				//second node

				while(current != null && !found)
				{
					if(current.info.compareTo(deleteItem) >= 0)
						found = true;
					else
					{
						trailCurrent = current;
						current = current.link;
					}
				}//end while

					if(current == null)   //Case 4
					System.out.println("Item to be deleted is "
							+ "not in the list.");
					else
						if(current.info.equals(deleteItem)) //item to be
							//deleted is in the list
						{
							if(first == current)    //Case 2
							{
								first = first.link;
								count--;
							}
							else                    //Case 3
							{
								trailCurrent.link = current.link;
								count--;
							}
						}
						else                   //Case 4
							System.out.println("Item to be deleted is "
									+ "not in the list.");
			}
		}//end else
	}//end deleteNode
}