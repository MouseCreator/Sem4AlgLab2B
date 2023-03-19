package BTree;

/**
 * Структура для передачі даних між вузлами при злитті та розбитті
 * @param median - медіана вузла
 * @param left - піддерево, вузли якого менші медіани
 * @param right - педдерево, вузли якого більші медіани
 */
record SmallNode(double median, BTreeNode left, BTreeNode right) {
}
