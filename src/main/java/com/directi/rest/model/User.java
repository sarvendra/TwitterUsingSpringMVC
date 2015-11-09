package com.directi.rest.model;

import java.util.Objects;
import java.util.UUID;

public class User
{
	private String userid;
    private String name; // full name
	private String email; // email
	private String password; // should be hashed
	private String role;

    public User() {}

	public User(String name, String email, String password)
    {
		this.userid = UUID.randomUUID().toString();
		this.name = name;
        this.email = email;
		this.password = password;
        this.role = "ADMIN";
	}

	public String getUserid()
    {
		return userid;
	}

	public String getName()
    {
		return name;
	}

    public String getEmail()
    {
        return email;
    }
	public String getPassword()
    {
		return password;
	}

	public String getRole()
    {
		return role;
	}

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof User
			&& Objects.equals(email, ((User) o).email);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(email);
	}

	@Override
	public String toString() {
		return "User{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
	}
}
