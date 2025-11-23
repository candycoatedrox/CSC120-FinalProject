public enum GameLocation {
    PATH,
    HILL,
    CABIN,
    CABINMIRROR,
    STAIRS,
    BASEMENT,

    BEFOREMIRROR,
    MIRROR,

    LEAVING;

    // --- ACCESSORS & CHECKS ---

    public GameLocation getForward() {
        switch (this) {
            case LEAVING: return LEAVING;
            case PATH: return HILL;
            case HILL: return CABIN;
            case CABIN: return STAIRS;
            case STAIRS: return BASEMENT;
            case BEFOREMIRROR: return MIRROR;
            default: return null;
        }
    }

    public GameLocation getBackward() {
        switch (this) {
            case PATH: return LEAVING;
            case HILL: return PATH;
            case CABIN: return HILL;
            case CABINMIRROR: return CABIN;
            case STAIRS: return CABIN;
            case BASEMENT: return STAIRS;
            case LEAVING: return PATH;
            default: return null;
        }
    }

    public boolean canGoInside() {
        switch (this) {
            case HILL: return true;
            case CABIN: return true;
            case STAIRS: return true;
            default: return false;
        }
    }

    public boolean canGoOutside() {
        switch (this) {
            case CABIN: return true;
            case STAIRS: return true;
            case BASEMENT: return true;
            default: return false;
        }
    }

    public boolean canGoDown() {
        switch (this) {
            case CABIN: return true;
            case STAIRS: return true;
            default: return false;
        }
    }

    public boolean canGoUp() {
        switch (this) {
            case STAIRS: return true;
            case BASEMENT: return true;
            default: return false;
        }
    }

    public String toString() {
        switch (this) {
            case PATH: return "path";
            case HILL: return "hill";
            case CABIN: return "cabin";
            case CABINMIRROR: return "mirror (cabin)";
            case STAIRS: return "stairs";
            case BASEMENT: return "basement";

            case BEFOREMIRROR: return "before mirror";
            case MIRROR: return "mirror";

            case LEAVING: return "leaving";
            
            default: return "n/a";
        }
    }
}
