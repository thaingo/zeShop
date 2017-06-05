package bzh.zomzog.zeshop.auth.domain;

import bzh.zomzog.zeshop.domain.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account extends AbstractAuditingEntity {

    /**
     * Serial Id
     */
    private static final long serialVersionUID = -4053429559820897623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String login;

    @JsonIgnore
    @NotNull
    @Column(name = "password_hash", length = 60)
    private String password;

    private boolean activated = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date")
    private ZonedDateTime resetDate = null;

    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    // TODO add cache
    // @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private final Set<Authority> authorities = new HashSet<>();

    public Account() {
    }

    public Account(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public Account id(final Long id) {
        this.id = id;
        return this;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param login the login to set
     */
    public Account login(final String login) {
        this.login = login;
        return this;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public Account password(final String password) {
        this.password = password;
        return this;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the activated
     */
    public boolean isActivated() {
        return this.activated;
    }

    /**
     * @param activated the activated to set
     */
    public Account activated(final boolean activated) {
        this.activated = activated;
        return this;
    }

    /**
     * @param activated the activated to set
     */
    public void setActivated(final boolean activated) {
        this.activated = activated;
    }

    /**
     * @return the activationKey
     */
    public String getActivationKey() {
        return this.activationKey;
    }

    /**
     * @param activationKey the activationKey to set
     */
    public Account activationKey(final String activationKey) {
        this.activationKey = activationKey;
        return this;
    }

    /**
     * @param activationKey the activationKey to set
     */
    public void setActivationKey(final String activationKey) {
        this.activationKey = activationKey;
    }

    /**
     * @return the langKey
     */
    public String getLangKey() {
        return this.langKey;
    }

    /**
     * @param langKey the langKey to set
     */
    public Account langKey(final String langKey) {
        this.langKey = langKey;
        return this;
    }

    /**
     * @param langKey the langKey to set
     */
    public void setLangKey(final String langKey) {
        this.langKey = langKey;
    }

    /**
     * @return the resetKey
     */
    public String getResetKey() {
        return this.resetKey;
    }

    /**
     * @param resetKey the resetKey to set
     */
    public Account resetKey(final String resetKey) {
        this.resetKey = resetKey;
        return this;
    }

    /**
     * @param resetKey the resetKey to set
     */
    public void setResetKey(final String resetKey) {
        this.resetKey = resetKey;
    }

    /**
     * @return the resetDate
     */
    public ZonedDateTime getResetDate() {
        return this.resetDate;
    }

    /**
     * @param resetDate the resetDate to set
     */
    public Account resetDate(final ZonedDateTime resetDate) {
        this.resetDate = resetDate;
        return this;
    }

    /**
     * @param resetDate the resetDate to set
     */
    public void setResetDate(final ZonedDateTime resetDate) {
        this.resetDate = resetDate;
    }

    /**
     * @param authorities the authorities to set
     */
    public Account authorities(final Set<Authority> authorities) {
        this.authorities.clear();
        this.authorities.addAll(authorities);
        return this;
    }

    /**
     * @return the authorities
     */
    public Set<Authority> getAuthorities() {
        return this.authorities;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Account [" + (this.id != null ? "id=" + this.id + ", " : "")
                + (this.login != null ? "login=" + this.login + ", " : "")
                + (this.password != null ? "password=" + this.password + ", " : "") + "activated=" + this.activated
                + ", "
                + (this.activationKey != null ? "activationKey=" + this.activationKey + ", " : "")
                + (this.langKey != null ? "langKey=" + this.langKey + ", " : "")
                + (this.resetKey != null ? "resetKey=" + this.resetKey + ", " : "")
                + (this.resetDate != null ? "resetDate=" + this.resetDate + ", " : "")
                + (this.authorities != null ? "authorities=" + this.authorities : "") + "]";
    }

}