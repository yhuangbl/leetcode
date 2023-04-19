class Solution986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> intersections = new ArrayList<int[]>();
        int alen = A.length;
        int blen = B.length;
        int aidx = 0;
        int bidx = 0;
        while (aidx < alen && bidx < blen) {
            int astart = A[aidx][0];
            int aend = A[aidx][1];
            int bstart = B[bidx][0];
            int bend = B[bidx][1];
            if (bstart >= astart && bstart <= aend) {
                int[] intersect = new int[2];
                intersect[0] = Math.max(astart, bstart);
                intersect[1] = Math.min(aend, bend);
                intersections.add(intersect);
                if (bend < aend) {
                    bidx++;
                }
                else {
                    aidx++;
                }
            }
            else if (astart >= bstart && astart <= bend) {
                int[] intersect = new int[2];
                intersect[0] = Math.max(astart, bstart);
                intersect[1] = Math.min(aend, bend);
                intersections.add(intersect);
                if (bend < aend) {
                    bidx++;
                }
                else {
                    aidx++;
                }
            }
            else if (bend == astart) {
                int[] intersect = new int[2];
                intersect[0] = bend;
                intersect[1] = bend;
                intersections.add(intersect);
                bidx++;
            }
            else if (aend == bstart) {
                int[] intersect = new int[2];
                intersect[0] = aend;
                intersect[1] = aend;
                intersections.add(intersect);
                aidx++;
            }
            else if (bend > aend) {
                aidx++;
            }
            else {
                bidx++;
            }
        }
        return intersections.toArray(new int[intersections.size()][2]);
    }
}