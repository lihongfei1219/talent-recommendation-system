#!/usr/bin/python
# -*- coding: utf-8 -*-
from segmenter import segment


class IDFLoader(object):
    def __init__(self, idf_path):
        self.idf_path = idf_path
        self.idf_freq = {}     # idf
        self.mean_idf = 0.0    # 均值
        self.load_idf()

    def load_idf(self):       # 从文件中载入idf
        cnt = 0
        with open(self.idf_path, 'r', encoding='utf-8') as f:
            for line in f:
                try:
                    word, freq = line.strip().split(' ')
                    cnt += 1
                except Exception as e:
                    pass
                self.idf_freq[word] = float(freq)

        print('Vocabularies loaded: %d' % cnt)
        self.mean_idf = sum(self.idf_freq.values()) / cnt


class TFIDF(object):
    def __init__(self, idf_path):
        self.idf_loader = IDFLoader(idf_path)
        self.idf_freq = self.idf_loader.idf_freq
        self.mean_idf = self.idf_loader.mean_idf

    def extract_keywords(self, sentence, topK=20):    # 提取关键词
        # 过滤
        seg_list = segment(sentence)

        freq = {}
        for w in seg_list:
            freq[w] = freq.get(w, 0.0) + 1.0
        total = sum(freq.values())

        for k in freq:   # 计算 TF-IDF
            freq[k] *= self.idf_freq.get(k, self.mean_idf) / total

        tags = sorted(freq, key=freq.__getitem__, reverse=True)  # 排序

        if topK:
            return tags[:topK]
        else:
            return tags


def run(document):
    #TODO 处理业务逻辑
    topK = 10
    idffile = 'target.txt'
    tdidf = TFIDF(idffile)
    tags = tdidf.extract_keywords(document, topK)
    print(tags)
    return tags




