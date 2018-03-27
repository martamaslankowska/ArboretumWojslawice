package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RoutePointEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public abstract class RoutePointDao implements BaseDao<RoutePointEntity> {}
