class Solution {
  public int search(ArrayReader reader, int target) {
    int l = 0;
    int r = 10000;

    while (l < r) {
      final int m = (l + r) / 2;
      if (reader.get(m) >= target)
        r = m;
      else
        l = m + 1;
    }

    return reader.get(l) == target ? l : -1;
  }
}
