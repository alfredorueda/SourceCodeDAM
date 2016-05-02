using UnityEngine;
using System.Collections;

public class PremioScript : MonoBehaviour {

	void Start () {
	
	}
	
	void Update () {
		transform.Rotate (new Vector3 (30, 50, 65) * Time.deltaTime);
	}
}
