package com.liferay.events.activities;

//	, BeaconManager.MonitoringListener, BeaconManager.RangingListener

//		beaconManager = new BeaconManager(getApplicationContext());
//		beaconManager.setMonitoringListener(this);
//		beaconManager.setRangingListener(this);

//		beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//			@Override
//			public void onServiceReady() {
//				scanId = beaconManager.startNearableDiscovery();
//				beaconManager.startMonitoring(new Region("sth", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));
//			}
//		});

//	@Override
//	public void onEnteredRegion(Region region, List<Beacon> list) {
//
//		beaconManager.startRanging(region);
//
//		for (Beacon beacon : list) {
//			Snackbar.make(_content, "Enter region: " + beacon.getName(), Snackbar.LENGTH_SHORT).show();
//		}
//		Log.d("asdas", list.toString());
//	}
//
//	@Override
//	public void onExitedRegion(Region region) {
//
//			Snackbar.make(_content, "Exited region", Snackbar.LENGTH_SHORT).show();
//	}
//
//	@Override
//	public void onBeaconsDiscovered(Region region, List<Beacon> list) {
//		for (Beacon beacon : list) {
//			Snackbar.make(_content, "Beacon discovered! " + beacon.getName(), Snackbar.LENGTH_SHORT).show();
//		}
//	}

