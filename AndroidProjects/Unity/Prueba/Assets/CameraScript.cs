using UnityEngine;
using System.Collections;

public class CameraScript : MonoBehaviour {

	public GameObject bola;
	private Vector3 offset;

	void Start () {
		offset = transform.position - bola.transform.position;
	}

	void Update () {
		transform.position = bola.transform.position + offset;
	}
}
