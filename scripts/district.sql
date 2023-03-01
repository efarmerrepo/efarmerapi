INSERT INTO DISTRICT(id,name,state_id) VALUES (1,'Nagpur','27');
INSERT INTO CITY(id,name,DISTRICT_id) VALUES (1,'Nagpur','1');

INSERT INTO ANDROID_APP_CONFIG(id,play_Store_Version,min_Version_Required,app_Url) VALUES (1,'1.4.0','1.2.0','https://www.playstore.com?id=com.efarmer');
INSERT INTO WEIGHT_UNIT(id,name) VALUES (1,'Kg');
INSERT INTO LAND_UNIT(id,name) VALUES (1,'Acre');
INSERT INTO BUYER_TYPE(id,name) VALUES (1,'Industry');
INSERT INTO CROP_CATEGORY(id,name) VALUES (1,'Fruits');


{
	"androidAppConfig": {
		"playStoreVersion": "1.4.0",
		"minVersionRequired": "1.2.0",
		"appUrl": "https://www.playstore.com?id=com.efarmer"
	},

	"weightUnit": {
		"en": [{
				"id": 1,
				"name": "Kg"
			},
			{
				"id": 2,
				"name": "Quintal"
			},
			{
				"id": 3,
				"name": "Ton"
			}
		],
		"hindi": [{
				"id": 1,
				"name": "Kg"
			},
			{
				"id": 2,
				"name": "Quintal"
			},
			{
				"id": 3,
				"name": "Ton"
			}
		]
	},
	"landUnit": {
		"en": [{
				"id": 1,
				"name": "Acre"
			},
			{
				"id": 2,
				"name": "Hectare"
			},
			{
				"id": 3,
				"name": "Biga"
			}
		],
		"hindi": [{
				"id": 1,
				"name": "Acre"
			},
			{
				"id": 2,
				"name": "Hectare"
			},
			{
				"id": 3,
				"name": "Biga"
			}
		]
	},
	"buyerType": {
		"en": [{
				"id": 1,
				"name": "Industry"
			},
			{
				"id": 2,
				"name": "Cold Storage"
			},
			{
				"id": 3,
				"name": "Dealer"
			},
			{
				"id": 4,
				"name": "APMC Market"
			}
		],
		"hindi": [{
				"id": 1,
				"name": "Industry"
			},
			{
				"id": 2,
				"name": "Cold Storage"
			},
			{
				"id": 3,
				"name": "Dealer"
			},
			{
				"id": 4,
				"name": "APMC Market"
			}
		]
	}

"cropCategories": {// We can add categories in this api, no need to create separate api
		"en": [{
				"id": 1,
				"name": "Fruits"
			},
			{
				"id": 2,
				"name": "Vegetables"
			},
			{
				"id": 3,
				"name": "Grains"
			},
			{
				"id": 4,
				"name": "Pulses"
			},
			{
				"id": 5,
				"name": "Spices"
			},
			{
				"id": 6,
				"name": "Flowers"
			},
			{
				"id": 7,
				"name": "Herbs"
			}
		]
	}

}
