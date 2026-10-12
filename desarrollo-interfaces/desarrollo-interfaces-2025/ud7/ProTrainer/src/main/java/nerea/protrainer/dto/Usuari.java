package nerea.protrainer.dto;

/**
 * Clase que representa un usuario {@code Usuari}.
 * Contiene la información de un usuario como el id, el nombre, el emai, la contraseña, la foto y si es instructor o no.
 * 
 * @author Nerea
 */
public class Usuari {

    /**
     * Variables relacionadas con los usuarios de la aplicación.
     * <p>
     * - `id` almacena el id del usuario. 
     * - `nom` almacena el nombre del usuario.
     * - `email` almacena el email del usuario.
     * - `passwordHash` almacena la contraseña del usuario.
     * - `foto` almacena las imagenes del usuario.
     * - `instructor` indica si el usuario es un instructor (`true`) o no (`false`).
     */
    private int Id;
    private String nom;
    private String email;
    private String passwordHash;
    private byte[] foto;
    private boolean instructor;
    
    /**
     * Constructor por defecto.
     */
    public Usuari(){
        
    }

    /**
     * Obtiene el Id del usuario.
     * @return Devuelve el Id del usuario.
     */
    public int getId() {
        return Id;
    }

    /**
     * Establece el Id del usuario.
     * @param Id Id del usuario.
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Devuelve el nombre del usuario.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del usuario.
     * @param nom NMombre dle usuario.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtiene el email del usuario.
     * @return Devuelve el email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * @param email Email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Devuelve la contraseña del usuario.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Establece la contraseña para el usuario.
     * @param passwordHash Contraseña del usuario.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Obtiene la foto del usuario.
     * @return Devuelve la foto del usuario.
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Establece la foto del usuario.
     * @param Foto Foto del usuario.
     */
    public void setFoto(byte[] Foto) {
        this.foto = Foto;
    }

    /**
     * Obtiene si el usuario es instructor.
     * @return {@code true} si el usuario es intructor, {@code false} si el usuario no es intructor.
     */
    public boolean isInstructor() {
        return instructor;
    }

    /**
     * Establece si el usuario es instructor o no.
     * @param instructor {@code true} si el usuario es un instructor, {@code false} en caso contrario
     */
    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    /**
     * Devuelve una cadena con los datos obtenidos del nombre.
     * @return Cadena con el nombre del usuario.
     */
    @Override
    public String toString() {
        return this.nom;
    }

}
