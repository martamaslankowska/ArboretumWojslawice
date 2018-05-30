package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.NewsImageEntity;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.NewsImage;

@Dao
public abstract class NewsImageDao extends BaseDao<NewsImageEntity>{

    @Query("SELECT IdNewsImage, IdNews, ExtraImage " +
            "FROM NewsImages " +
            "WHERE IdNewsImage IN (:id)")
    public abstract NewsImage getById(int id);


    @Query("SELECT IdNewsImage, IdNews, ExtraImage " +
            "FROM NewsImages " +
            "WHERE IdNews IN (:idNews)")
    public abstract List<NewsImage> getAllByNewsId(int idNews);

}
