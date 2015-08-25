package model.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Index;

/**
 *
 * @author Alex
 */

@Entity
@Table(name="usuario", uniqueConstraints=@UniqueConstraint(columnNames = {"val_login", "val_senha"}))
@NamedQueries({
    @NamedQuery(name="Usuario.findByLogin", query = "SELECT u FROM Usuario AS u " + 
        "WHERE u.login = :lgn AND u.senha = :sen")
})

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Index(name = "idx_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "cod_usuario", nullable = false, unique = true)
    private int codigo;

    @Column(name = "des_nome", nullable = true)
    private String nome;

    @Column(name = "val_login", nullable = false)
    private String login;

    @Column(name = "val_senha", nullable = false, length = 32)
    private String senha;
    
    @Column(name = "des_email", nullable = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_ultimologin", nullable = true)
    private Calendar ultimologin;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_cadastro", nullable = false)
    private Calendar dataCadastro;
    
    public Calendar getUltimologin() {
        return ultimologin;
    }

    public void setUltimologin(Calendar ultimologin) {
        this.ultimologin = ultimologin;
    }

    public Usuario(){	
        super();
    }
    
    public Usuario(int codigo, Calendar dataCadastro){
        this.codigo = codigo;
        this.dataCadastro = dataCadastro;
    }

    public Usuario(int codigo, String nome, String login, String senha, Calendar dataCadastro) {
        this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getUltimoLoginFormatada(){
        String sr = "";
        if(this.ultimologin != null){
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
            sr = s.format(this.ultimologin.getTime());
            sr += " - ";
            SimpleDateFormat t = new SimpleDateFormat("HH:mm");
            sr += t.format(this.ultimologin.getTime());
        }
        return sr;
    }
    
    public String getDataCadastroFormatada(){
        String sr = "";
        if(this.dataCadastro != null){
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
            sr = s.format(this.dataCadastro.getTime());
            sr += " - ";
            SimpleDateFormat t = new SimpleDateFormat("HH:mm");
            sr += t.format(this.dataCadastro.getTime());
        }
        return sr;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.tecnojabur.Model.Usuario[ chave=" + id + " ]";
    }    

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}