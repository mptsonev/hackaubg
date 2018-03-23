package hackatonkm.response;

public class TestResponse implements GeneralResponse {

	private String hello;

	public TestResponse(String hello) {
		this.hello = hello;
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hello == null) ? 0 : hello.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestResponse other = (TestResponse) obj;
		if (hello == null) {
			if (other.hello != null)
				return false;
		} else if (!hello.equals(other.hello))
			return false;
		return true;
	}

}
