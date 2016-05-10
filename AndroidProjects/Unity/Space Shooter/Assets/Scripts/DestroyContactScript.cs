using UnityEngine;
using System.Collections;

public class DestroyContactScript : MonoBehaviour {

	public GameObject explosion;
	public GameObject playerExplosion;
	public int scoreValue;
	public GameControllerScript gameController;

	void Start() {
		GameObject gameControllerObject = GameObject.FindWithTag ("GameController");
		if (gameControllerObject != null) {
			gameController = gameControllerObject.GetComponent<GameControllerScript> ();
		}
		if (gameController == null) {
			Debug.Log ("Not found!!!");
		}
	}

	void OnTriggerEnter(Collider other) {
		if (other.CompareTag("Boundary")) {
			return;
		}
		Instantiate (explosion, transform.position, transform.rotation);
		if (other.tag == "Player") {
			Instantiate (playerExplosion, other.transform.position, other.transform.rotation);
			gameController.GameOver ();
		}
		gameController.AddScore (scoreValue);
		Destroy (other.gameObject);
		Destroy (gameObject);
	}
}
