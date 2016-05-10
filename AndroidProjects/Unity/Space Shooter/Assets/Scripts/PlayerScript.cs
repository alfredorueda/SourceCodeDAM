using UnityEngine;
using UnityEngine.UI;
using System.Collections;

[System.Serializable]
public class Boundary {
	public float xMin, xMax, yMin, yMax;
}

public class PlayerScript : MonoBehaviour {

	private Rigidbody rb;
	public Boundary boundary;
	public float speed = 1;
	public float tilt = 1;
	public float fireRate = 0.5f;
	private float nextFire = 0.0f;
	public GameObject shot;
	public Transform shotspawn;

	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody> ();
	}

	void Update() {
		if (Input.GetButton ("Fire1") && (Time.time > nextFire)) {
			nextFire = Time.time + fireRate;
			GameObject clone = Instantiate (shot, shotspawn.position, shotspawn.rotation) as GameObject;
			GetComponent<AudioSource>().Play ();
		}
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		float hor = Input.GetAxis ("Horizontal");
		float ver = Input.GetAxis ("Vertical");
		rb.velocity = new Vector3 (hor, 0.0f, ver) * speed;
		rb.position = new Vector3 (
			Mathf.Clamp (rb.position.x, boundary.xMin, boundary.xMax),
			0.0f,
			Mathf.Clamp (rb.position.z, boundary.yMin, boundary.yMax)
		);
		rb.rotation = Quaternion.Euler (0.0f, 0.0f, rb.velocity.x * -tilt);
	}
}
