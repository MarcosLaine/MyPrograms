import tkinter as tk

class Node:
    """Class to represent a node in the linked list."""
    def __init__(self, data):
        self.data = data  # Data stored in the node
        self.next = None  # Pointer to the next node

class LinkedList:
    """Class to represent the linked list."""
    def __init__(self, canvas):
        self.head = None  # Initialize the head of the list
        self.canvas = canvas
        self.node_positions = {}  # To keep track of positions of nodes on canvas
        self.node_ids = {}  # To keep track of node IDs on canvas
        self.edge_ids = []  # To keep track of edge IDs on canvas

    def insert_at_beginning(self, data):
        """Insert a node at the beginning of the list."""
        new_node = Node(data)
        new_node.next = self.head  # Link the new node to the previous head
        self.head = new_node  # Update the head to the new node

    def insert_at_end(self, data):
        """Insert a node at the end of the list."""
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        last = self.head
        while last.next:
            last = last.next  # Traverse to the last node
        last.next = new_node  # Link the last node to the new node

    def insert_after_node(self, prev_node_data, data):
        """Insert a node after a given node."""
        prev_node = self.find_node(prev_node_data)
        if not prev_node:
            print('Previous node not found.')
            return
        new_node = Node(data)
        new_node.next = prev_node.next  # Link new node to the next node
        prev_node.next = new_node  # Link previous node to the new node

    def delete_node(self, key):
        """Delete a node from the list."""
        temp = self.head
        prev = None
        # If head node holds the key to be deleted
        if temp and temp.data == key:
            self.head = temp.next  # Move head to the next node
            temp = None
            return
        # Search for the key to be deleted
        while temp and temp.data != key:
            prev = temp
            temp = temp.next
        # If key was not found
        if temp is None:
            print('Node with data {} not found.'.format(key))
            return
        # Unlink the node from the linked list
        prev.next = temp.next
        temp = None

    def find_node(self, data):
        """Find a node with the given data."""
        current = self.head
        while current:
            if current.data == data:
                return current  # Node found
            current = current.next
        return None  # Node not found

    def visualize(self, title='Linked List'):
        """Visualize the linked list using Tkinter Canvas."""
        # Clear the canvas
        self.canvas.delete('all')
        # Set title
        self.canvas.create_text(400, 20, text=title, font=('Arial', 16))

        # Draw the linked list
        node = self.head
        idx = 0
        x_start = 50  # Starting x position
        y = 100  # y position for all nodes

        node_width = 60
        node_height = 40
        spacing = 20  # Space between nodes

        self.node_positions = {}
        self.node_ids = {}
        self.edge_ids = []

        while node:
            x = x_start + idx * (node_width + spacing)
            # Draw node rectangle
            rect_id = self.canvas.create_rectangle(x, y, x+node_width, y+node_height, fill='lightblue')
            # Draw data text
            text_id = self.canvas.create_text(x+node_width/2, y+node_height/2, text=str(node.data))
            # Save positions and IDs
            self.node_positions[node] = (x, y)
            self.node_ids[node] = (rect_id, text_id)
            # Draw arrow to next node, if exists
            if node.next:
                next_x = x_start + (idx+1) * (node_width + spacing)
                line_id = self.canvas.create_line(x+node_width, y+node_height/2, next_x, y+node_height/2, arrow=tk.LAST)
                self.edge_ids.append(line_id)
            node = node.next
            idx += 1

if __name__ == '__main__':
    root = tk.Tk()
    root.title('Linked List Visualization')
    canvas = tk.Canvas(root, width=800, height=200)
    canvas.pack()

    ll = LinkedList(canvas)

    operations = [
        ('insert_at_end', 1, 'Insert at end: 1'),
        ('insert_at_end', 2, 'Insert at end: 2'),
        ('insert_at_end', 3, 'Insert at end: 3'),
        ('insert_at_beginning', 0, 'Insert at beginning: 0'),
        ('insert_after_node', (2, 2.5), 'Insert after node 2: 2.5'),
        ('delete_node', 0, 'Delete node: 0'),
        ('delete_node', 2.5, 'Delete node: 2.5'),
        ('delete_node', 3, 'Delete node: 3'),
    ]

    def perform_operations(index=0):
        if index >= len(operations):
            return
        op = operations[index]
        method_name = op[0]
        args = op[1]
        title = op[2]
        method = getattr(ll, method_name)
        if isinstance(args, tuple):
            method(*args)
        else:
            method(args)
        ll.visualize(title)
        # Schedule the next operation after a delay
        root.after(1500, perform_operations, index+1)

    # Create a Start button
    start_button = tk.Button(root, text='Start', command=perform_operations)
    start_button.pack()

    root.mainloop()
