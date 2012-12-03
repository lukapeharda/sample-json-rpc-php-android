<?php

class Application_Model_Data
{
	/**
	 * Fetches data 
	 * 
	 * @param integer $num
	 * @return array
	 */
	public function getData($num) 
	{
		$data = array();
		
		for ($a = 0; $a < $num; $a++) {
			$data[] = array(
				'title' => 'Codeforest.net',
				'number' => $a + 1,
				'datetime' => date('Y-m-d H:i:s')
			);
		}
		
		return $data;
	}
	
	/**
	 * Test method
	 * 
	 * @return string
	 */
	public function test()
	{
		return 'Hello XMLRPC!';
	}
}

