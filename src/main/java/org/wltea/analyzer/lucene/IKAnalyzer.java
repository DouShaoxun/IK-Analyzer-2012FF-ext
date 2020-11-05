/**
 * IK 中文分词  版本 7.3.0
 * IK Analyzer release 7.3.0
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 */
package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * IK分词器，Lucene Analyzer接口实现
 * 兼容Solr 7.3.0版本
 */
public final class IKAnalyzer extends Analyzer {

    private boolean useSmart;
    private String filePath = "";

    public boolean useSmart() {
        return useSmart;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    /**
     * IK分词器Lucene  Analyzer接口实现类
     *
     * 默认细粒度切分算法
     */
    public IKAnalyzer() {
        this(false, "");
    }

    /**
     * 默认细粒度切分算法
     * @param filePath IKAnalyzer.cfg.xml
     */
    public IKAnalyzer(String filePath) {
        this(false, filePath);
    }

    public IKAnalyzer(boolean useSmart, String filePath) {
        this.useSmart = useSmart;
        this.filePath = filePath;
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public IKAnalyzer(boolean useSmart) {
        super();
        this.useSmart = useSmart;
        this.filePath = "";
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer ikTokenizer  = new IKTokenizer(this.useSmart(), this.filePath);
        return new TokenStreamComponents(ikTokenizer);
    }
}
