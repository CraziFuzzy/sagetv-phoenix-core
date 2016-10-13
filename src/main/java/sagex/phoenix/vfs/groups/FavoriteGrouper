package sagex.phoenix.vfs.groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sagex.phoenix.vfs.IMediaFile;
import sagex.phoenix.vfs.IMediaResource;

public class GenreGrouper implements IGrouper, IMultiGrouper {

    public String getGroupName(IMediaResource res) {
        sage.Favorite favorite = null;
        if (res instanceof IMediaFile) {
            List<sage.Favorite> favorites = ((IMediaFile) res).GetFavoritesForAiring();
            if (favorites != null && favorites.size() > 0) {
                favorite = favorites.get(0);
            }
        }
        if (favorite != null) return favorite.GetFavoriteDescription();
        return null;
    }

    @Override
    public List<String> getGroupNames(IMediaResource res) {
        if (res instanceof IMediaFile) {
            List<sage.Favorite> favorites = ((IMediaFile) res).GetFavoritesForAiring();
            if (favorites != null && favorites.size() > 0) {
                List<String> l = new ArrayList<String>();
                for (sage.Favorite f : favorites) {
                    l.add(f.GetFavoriteDescription());
                }
                return l;
            } else {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }
}
