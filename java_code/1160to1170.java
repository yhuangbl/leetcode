class FileSystem1166 {

    Map<String, Integer> fileStructure;

    public FileSystem() {
        fileStructure = new HashMap<String, Integer>();
    }

    public boolean createPath(String path, int value) {
        boolean success = false;
        if (path.length() > 1 && path.charAt(0) == '/' && !fileStructure.containsKey(path)) {
            int lastSlashIdx = path.lastIndexOf('/');
            if (lastSlashIdx != -1) {
                if (lastSlashIdx == 0) { // root
                    success = true;
                    fileStructure.put(path, value);
                } else {
                    String parent = path.substring(0, lastSlashIdx);
                    if (fileStructure.containsKey(parent)) {
                        success = true;
                        fileStructure.put(path, value);
                    }
                }
            }
        }
        return success;
    }

    public int get(String path) {
        return fileStructure.getOrDefault(path, -1);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */