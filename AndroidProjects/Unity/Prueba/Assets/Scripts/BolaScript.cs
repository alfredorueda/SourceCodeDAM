using UnityEngine;
using System.Collections;

public class BolaScript : MonoBehaviour {

	private Rigidbody rigidbody;
	public float velocidad = 1.0f;

	void Start () {
		rigidbody = GetComponent<Rigidbody> ();
	}

	void FixedUpdate () {
		float x = Input.GetAxis ("Horizontal");
		float z = Input.GetAxis ("Vertical");

		Vector3 fuerza = velocidad * new Vector3 (x, 0.0f, z);
		rigidbody.AddForce (fuerza);
	}
}
