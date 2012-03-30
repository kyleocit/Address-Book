package addressbook;
/*
 * @(#)LinkedListClass.java	1.0 10/10/20
 *
 * Source code provided by Professor Larry Page.
 * Modified by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                  LinkedListClass
 * --------------------------------------------------
 *  #LinkedListNode
 *    info: DataElement
 *    link: Node
 *  #first: LinkedListNode
 *  #last: LinkedListNode
 *  #count: int
 * --------------------------------------------------
 *  +LinkedListClass()
 *  +isEmptyList(): boolean
 *  +print(): void
 *  +length(): int
 *  +front(): DataElement
 *  +back(): DataElement
 *  +search(DataElement): boolean
 *  +insertFirst(DataElement): void
 *  +insertLast(DataElement): void
 *  +deleteNode(DataElement): void
 *  -copy(LinkedListClass): void
 *  +copyList(LinkedListClass): void
 *  +LinkedListClass(LinkedListClass)
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - original source code provided by professor
 */

public abstract class LinkedListClass
{
	protected class LinkedListNode
	{
		DataElement info;
		LinkedListNode link;
	}

	protected LinkedListNode first; //variable to store the
	//address of the first
	//node of the list
	protected LinkedListNode last;  //variable to store the
	//address of the last
	//node of the list
	protected int count;  //variable to store the number of nodes
	//in the list

	//default constructor
	//Initializes the list to an empty state.
	//Postcondition: first = null, last = null,
	//               count = 0
	public LinkedListClass()
	{
		first = null;
		last = null;
		count = 0;
	}

	//Method to determine whether the list is empty.
	//Postcondition: Returns true if the list is empty;
	//               false otherwise.
	public boolean isEmptyList()
	{
		return (first == null);
	}

	//Method to initialize the list to an empty state.
	//Postcondition: first = null, last = null,
	//               count = 0
	public void initializeList()
	{
		first = null;
		last = null;
		count = 0;
	}

	//Method to output the data contained in each node.
	public void print()
	{
		LinkedListNode current; //variable to traverse the list

		current = first;    //set current so that it points to
		//the first node
		while(current != null) //while more data to print
		{
			System.out.print(current.info + " ");
			current = current.link;
		}
	}//end print

	//Method to return the number of nodes in the list.
	//Postcondition: The value of count is returned.
	public int length()
	{
		return count;
	}


	//Method to return a reference of the object containing
	//the data of the first node of the list.
	//Precondition: The list must exist and must not be empty.
	//Postcondition: The reference of the object that
	//               contains the info of the first node
	//               is returned.
	public DataElement front()
	{
		DataElement temp = first.info.getCopy();
		return temp;
	}

	//Method to return a reference of object containing
	//the data of the last node of the list.
	//Precondition: The list must exist and must not be empty.
	//Postcondition: The reference of the object that
	//               contains the info of the last node
	//               is returned.
	public DataElement back()
	{
		DataElement temp = last.info.getCopy();
		return temp;
	}

	//Method to determine whether searchItem is in the list.
	//Postcondition: Returns true if searchItem is found
	//               in the list; false otherwise.
	public abstract boolean search(DataElement searchItem);

	//Method to insert newItem in the list.
	//Postcondition: first points to the new list
	//               and newItem is inserted at the
	//               beginning of the list. Also,
	//               last points to the last node and
	//               count is incremented by 1.
	public void insertFirst(DataElement newItem)
	{
		LinkedListNode newNode;     //variable to create the
		//new node

		newNode = new LinkedListNode();     //create the new node
		newNode.info = newItem.getCopy();   //assign a copy of
		//newItem to the node
		newNode.link = first;   //insert newNode before first
		first = newNode;        //make first point to the
		//actual first node

		if(last == null)        //if the list was empty, newNode is
			//also the last node in the list
			last = newNode;

		count++;
	}

	//Method to insert newItem at the end of the list.
	//Postcondition: first points to the new list and
	//               newItem is inserted at the end
	//               of the list. Also, last points to
	//               the last node and
	//               count is incremented by 1.
	public void insertLast(DataElement newItem)
	{
		LinkedListNode newNode; //variable to create the new node

		newNode = new LinkedListNode();     //create the new node
		newNode.info = newItem.getCopy();   //assign a copy of
		//newItem to the node
		newNode.link = null;         //set the link field of
		//newNode to null

		if(first == null)   //if the list is empty, newNode is
			//both the first and last node
		{
			first = newNode;
			last = newNode;
		}
		else     //if the list is not empty, insert newNode after last
		{
			last.link = newNode; //insert newNode after last
			last = newNode;     //set last to point to the actual last node
		}

		count++;
	}//end insertLast

	//Method to delete deleteItem from the list.
	//Postcondition: If found, the node containing
	//               deleteItem is deleted from the
	//               list. Also first points to the first
	//               node, last points to the last
	//               node of the updated list, and count
	//               is decremented by 1.
	public abstract void deleteNode(DataElement deleteItem);

	//Method to return a reference of the copy of otherList.
	private void copy(LinkedListClass otherList)
	{
		LinkedListNode newNode; //variable to create a node
		LinkedListNode current; //variable to traverse the list

		first = null;  //make this list empty

		if(otherList.first == null) //otherList is empty
		{
			first = null;
			last = null;
			count = 0;
		}
		else
		{
			count = otherList.count;
			current = otherList.first;  //current points to the
			//list to be copied

			//copy the first element
			first = new LinkedListNode();         //create the node
			first.info = current.info.getCopy();  //copy the info
			first.link = null;   //set the link field of
			//the node to null
			last = first;        //make last point to the first node
			current = current.link; //make current point to the next
			//node of the list being copied

			//copy the remaining list
			while(current != null)
			{
				newNode = new LinkedListNode();
				newNode.info = current.info.getCopy();
				newNode.link = null;
				last.link = newNode;
				last = newNode;
				current = current.link;
			}//end while
		}//end else
	}//end copy

	//Method to return a reference of the copy of otherList.
	public void copyList(LinkedListClass otherList)
	{
		if(this != otherList)  //avoid self-copy
			copy(otherList);
	}

	//copy constructor
	public LinkedListClass(LinkedListClass otherList)
	{
		copy(otherList);
	}//end copy constructor
}