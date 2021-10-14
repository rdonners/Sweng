import unittest
from LCA import Node 
from LCA import findLCA

class TestLCA(unittest.TestCase):

   
    def test_PrintsTrue(self):
        self.assertTrue(True)
    def test_LCA(self):
       
        root = Node(1)
        root.left = Node(2)
        root.right = Node(3)
        root.left.left = Node(4)
        root.left.right = Node(5)
        root.right.left = Node(6)
        root.right.right = Node(7)

        self.assertEqual(2,findLCA(root, 4, 5), "Normal 1 Error Message")
        self.assertEqual(1,findLCA(root, 4, 6), "Normal 2 Error Message")
        self.assertEqual(1,findLCA(root, 3, 4), "Normal 3 Error Message")
        self.assertEqual(2,findLCA(root, 2, 4), "Normal 4 Error Message")
    def test_element_LCA(self):

        root = Node(1)
        self.assertEqual(1,findLCA(root, 1, 1), "Single Elem Error Message")

if __name__ == '__main__':
        unittest.main()
    
