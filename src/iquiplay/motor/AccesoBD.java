package iquiplay.motor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccesoBD extends SQLiteOpenHelper {

	static final String NombreBD = "ChrapitaBD.db";
	static final String TProducto = "producto";
	static final String Fidusuario = "id";
	static final String Fusuario = "usuario";
	static final String Fcodigo = "codigo";
	static final String Festado = "activado";
	static final String TNiveles = "niveles";
	static final String Fidnivel = "idnivel";
	static final String Fbloqueado = "blogueado";
	static final String Fpuntos = "puntos";

	public AccesoBD(Context context) {
		// El valor de 1 en la siguiente línea REPRESENTA EL NÚMERO DE VERSIÓN
		// DE LA BASE DE DATOS
		// EN EL FUTURO SI USTED HACE CAMBIOS EN LA BASE DE DATOS, USTED
		// NECESITA ESTE NÚMERO para incrementar
		// Si lo hace, EL MÉTODO onUpgrade () para obtener automáticamente
		// ACTIVA
		super(context, NombreBD, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Establecer unas tablas de base de datos nuevos SI NO EXISTEN EN LA
		// BASE DE DATOS
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TProducto + " (" + Fidusuario
				+ " INTEGER PRIMARY KEY , "+ Fusuario
				+ " VARCHAR, " + Fcodigo + " VARCHAR, " + Festado
				+ " BOOLEAN" + ")");

		db.execSQL("CREATE TABLE IF NOT EXISTS " + TNiveles + " (" + Fidnivel
				+ " INTEGER PRIMARY KEY , " + Fbloqueado + " BOOLEAN, "
				+ Fpuntos + " INTEGER" + ")");

		ContentValues cv2 = new ContentValues();		
		cv2.put(Fidusuario, 1);
		cv2.put(Fusuario, "prueba");
		cv2.put(Fcodigo, "prueba");
		cv2.put(Festado, false);
		db.insert(TProducto, NombreBD, cv2);
		
		// Opcionalmente rellenar previamente la tabla con algunos valores
		ContentValues cv = new ContentValues();
		cv.put(Fidnivel, 1);
		cv.put(Fbloqueado, false);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 2);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, "0");
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 3);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 4);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 5);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 6);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 7);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 8);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);

		cv.put(Fidnivel, 9);
		cv.put(Fbloqueado, true);
		cv.put(Fpuntos, 0);
		db.insert(TNiveles, NombreBD, cv);
		
		
		

		
		
		

		/*
		 * MORE ADVANCED EXAMPLES OF USAGE
		 * 
		 * db.execSQL("CREATE TRIGGER fk_empdept_deptid " + " BEFORE INSERT "+
		 * " ON "+employeeTable+ " FOR EACH ROW BEGIN"+
		 * " SELECT CASE WHEN ((SELECT "
		 * +colDeptID+" FROM "+deptTable+" WHERE "+colDeptID
		 * +"=new."+colDept+" ) IS NULL)"+
		 * " THEN RAISE (ABORT,'Foreign Key Violation') END;"+ "  END;");
		 * 
		 * db.execSQL("CREATE VIEW "+viewEmps+
		 * " AS SELECT "+employeeTable+"."+colID+" AS _id,"+
		 * " "+employeeTable+"."+colName+","+ " "+employeeTable+"."+colAge+","+
		 * " "+deptTable+"."+colDeptName+""+
		 * " FROM "+employeeTable+" JOIN "+deptTable+
		 * " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID );
		 */
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Este método elimina la tabla existente y luego llama al onCreate
		// MÉTODO () de nuevo para volver a crear una nueva tabla
		// ESTO SIRVE PARA RESTAURAR SUSTANCIAL DE LA BASE DE DATOS
		// LUGAR podrá modificar las tablas existentes mediante la adición /
		// eliminación de columnas / filas / VALORES ENTONCES NO HAY DATOS
		// existentes se perderían
		db.execSQL("DROP TABLE IF EXISTS " + TNiveles);
		db.execSQL("DROP TABLE IF EXISTS " + TProducto);
		onCreate(db);
	}
	public void Registrar(){
		
	}
	public boolean ProductoActivado() {
		
		SQLiteDatabase myDB = this.getReadableDatabase();
		String[] parametro = new String[] { String.valueOf(1) };
		Cursor myCursor = myDB.rawQuery("SELECT " + Festado + " FROM "
				+ TProducto + " WHERE " + Fidusuario + "=?", parametro);
		myCursor.moveToFirst();
		
		int index = myCursor.getColumnIndex(Festado);
		int temprespuesta = myCursor.getInt(index);
		boolean respuesta = true;

		if (temprespuesta == 1)
			respuesta = true;
		else if (temprespuesta == 0)
			respuesta = false;

		myCursor.close();
		return respuesta;
		
	

	}

	public long RegistrarProducto(String Usuario, String codigo) {
		
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Fusuario, Usuario);
		cv.put(Fcodigo, codigo);
		cv.put(Festado, true);
		int numRowsAffected = myDB.update(TProducto, cv, Fidusuario + "=?",
				new String[] { String.valueOf(1) });
		return numRowsAffected;
		
		
		// para desbloquear o bloquear un nivel
	}

	public boolean NivelBloqueado(int nivel) {
		// Este metodo es pa devolver el estado del nivel
		SQLiteDatabase myDB = this.getReadableDatabase();
		String[] parametro = new String[] { String.valueOf(nivel) };
		Cursor myCursor = myDB.rawQuery("SELECT " + Fbloqueado + " FROM "
				+ TNiveles + " WHERE " + Fidnivel + "=?", parametro);
		myCursor.moveToFirst();
		int index = myCursor.getColumnIndex(Fbloqueado);
		int temprespuesta = myCursor.getInt(index);
		boolean respuesta = true;

		if (temprespuesta == 1)
			respuesta = true;
		else if (temprespuesta == 0)
			respuesta = false;

		myCursor.close();
		return respuesta;
	}

	public int ActualizarBloqueoNivel(int ID, boolean desbloquear) {
		// para desbloquear o bloquear un nivel
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Fbloqueado, desbloquear);
		int numRowsAffected = myDB.update(TNiveles, cv, Fidnivel + "=?",
				new String[] { String.valueOf(ID) });
		return numRowsAffected;
	}

	public int ActualizarBloqueoNivel(int nivel, int puntos, boolean desbloquear) {
		// para desbloquear o bloquear un nivel
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Fbloqueado, desbloquear);
		cv.put(Fpuntos, puntos);
		int numRowsAffected = myDB.update(TNiveles, cv, Fidnivel + "=?",
				new String[] { String.valueOf(nivel) });
		return numRowsAffected;
	}

	public int ObtenerPuntajeNivel(int nivel) {
		// para objenet el punytaje de un nivel
		SQLiteDatabase myDB = this.getWritableDatabase();
		// ContentValues cv = new ContentValues();
		String[] parametro = new String[] { String.valueOf(nivel) };
		Cursor myCursor = myDB.rawQuery("SELECT " + Fpuntos + " FROM "
				+ TNiveles + " WHERE " + Fidnivel + "=?", parametro);
		myCursor.moveToFirst();
		int index = myCursor.getColumnIndex(Fpuntos);
		int respuesta = Integer.parseInt(myCursor.getString(index));
		myCursor.close();
		return respuesta;
	}

	public int ActualizarPuntosNivel(int nivel, int puntos) {
		// para actualizar puntos de u nivel
		if (puntos > ObtenerPuntajeNivel(nivel)) {
			SQLiteDatabase myDB = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(Fpuntos, puntos);
			int filasafectadas = myDB.update(TNiveles, cv, Fidnivel + "=?",
					new String[] { String.valueOf(nivel) });

			return filasafectadas;
		}
		return 0;
	}

	/*
	 * MORE ADVANCED EXAMPLES OF USAGE
	 * 
	 * void AddEmployee(String _name, int _age, int _dept) { SQLiteDatabase db=
	 * this.getWritableDatabase(); ContentValues cv=new ContentValues();
	 * cv.put(colName, _name); cv.put(colAge, _age); cv.put(colDept, _dept);
	 * //cv.put(colDept,2); db.insert(employeeTable, colName, cv); db.close(); }
	 * 
	 * int getEmployeeCount() { SQLiteDatabase db=this.getWritableDatabase();
	 * Cursor cur= db.rawQuery("Select * from "+employeeTable, null); int x=
	 * cur.getCount(); cur.close(); return x; }
	 * 
	 * Cursor getAllEmployees() { SQLiteDatabase db=this.getWritableDatabase();
	 * //Cursor cur=
	 * db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge
	 * +" from "+employeeTable, new String [] {}); Cursor cur=
	 * db.rawQuery("SELECT * FROM "+viewEmps,null); return cur; }
	 * 
	 * public int GetDeptID(String Dept) { SQLiteDatabase
	 * db=this.getReadableDatabase(); Cursor c=db.query(deptTable, new
	 * String[]{colDeptID+" as _id",colDeptName},colDeptName+"=?", new
	 * String[]{Dept}, null, null, null); //Cursor
	 * c=db.rawQuery("SELECT "+colDeptID
	 * +" as _id FROM "+deptTable+" WHERE "+colDeptName+"=?", new String
	 * []{Dept}); c.moveToFirst(); return c.getInt(c.getColumnIndex("_id")); }
	 * 
	 * public String GetDept(int ID) { SQLiteDatabase
	 * db=this.getReadableDatabase(); String[] params=new
	 * String[]{String.valueOf(ID)}; Cursor
	 * c=db.rawQuery("SELECT "+colDeptName+" FROM"+
	 * deptTable+" WHERE "+colDeptID+"=?",params); c.moveToFirst(); int index=
	 * c.getColumnIndex(colDeptName); return c.getString(index); }
	 * 
	 * public Cursor getEmpByDept(String Dept) { SQLiteDatabase
	 * db=this.getReadableDatabase(); String [] columns=new
	 * String[]{"_id",colName,colAge,colDeptName}; Cursor c=db.query(viewEmps,
	 * columns, colDeptName+"=?", new String[]{Dept}, null, null, null); return
	 * c; }
	 * 
	 * public int UpdateEmp(String _name, int _age, int _dept, int _eid) {
	 * SQLiteDatabase db=this.getWritableDatabase(); ContentValues cv=new
	 * ContentValues(); cv.put(colName, _name); cv.put(colAge, _age);
	 * cv.put(colDept, _dept); return db.update(employeeTable, cv, colID+"=?",
	 * new String []{String.valueOf(_eid)}); }
	 * 
	 * public void DeleteEmp(String _name, int _age, int _dept, int _eid) {
	 * SQLiteDatabase db=this.getWritableDatabase();
	 * db.delete(employeeTable,colID+"=?", new String []
	 * {String.valueOf(_eid)}); db.close(); }
	 */

}
