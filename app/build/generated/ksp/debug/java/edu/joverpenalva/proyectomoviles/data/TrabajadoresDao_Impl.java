package edu.joverpenalva.proyectomoviles.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TrabajadoresDao_Impl implements TrabajadoresDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Result> __insertionAdapterOfResult;

  private final EntityDeletionOrUpdateAdapter<Result> __deletionAdapterOfResult;

  public TrabajadoresDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfResult = new EntityInsertionAdapter<Result>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Result` (`apellidos`,`contraseña`,`dni`,`email`,`especialidad`,`idTrabajador`,`nombre`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Result entity) {
        statement.bindString(1, entity.getApellidos());
        statement.bindString(2, entity.getContraseña());
        statement.bindString(3, entity.getDni());
        statement.bindString(4, entity.getEmail());
        statement.bindString(5, entity.getEspecialidad());
        statement.bindString(6, entity.getIdTrabajador());
        statement.bindString(7, entity.getNombre());
      }
    };
    this.__deletionAdapterOfResult = new EntityDeletionOrUpdateAdapter<Result>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Result` WHERE `idTrabajador` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Result entity) {
        statement.bindString(1, entity.getIdTrabajador());
      }
    };
  }

  @Override
  public Object insertTrabajador(final Result trabajador,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfResult.insert(trabajador);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTrabajador(final Result trabajador,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfResult.handle(trabajador);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTrabajador(final Continuation<? super Result> $completion) {
    final String _sql = "SELECT * FROM result LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Result>() {
      @Override
      @Nullable
      public Result call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfApellidos = CursorUtil.getColumnIndexOrThrow(_cursor, "apellidos");
          final int _cursorIndexOfContraseA = CursorUtil.getColumnIndexOrThrow(_cursor, "contraseña");
          final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfEspecialidad = CursorUtil.getColumnIndexOrThrow(_cursor, "especialidad");
          final int _cursorIndexOfIdTrabajador = CursorUtil.getColumnIndexOrThrow(_cursor, "idTrabajador");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final Result _result;
          if (_cursor.moveToFirst()) {
            final String _tmpApellidos;
            _tmpApellidos = _cursor.getString(_cursorIndexOfApellidos);
            final String _tmpContraseña;
            _tmpContraseña = _cursor.getString(_cursorIndexOfContraseA);
            final String _tmpDni;
            _tmpDni = _cursor.getString(_cursorIndexOfDni);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpEspecialidad;
            _tmpEspecialidad = _cursor.getString(_cursorIndexOfEspecialidad);
            final String _tmpIdTrabajador;
            _tmpIdTrabajador = _cursor.getString(_cursorIndexOfIdTrabajador);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            _result = new Result(_tmpApellidos,_tmpContraseña,_tmpDni,_tmpEmail,_tmpEspecialidad,_tmpIdTrabajador,_tmpNombre);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
